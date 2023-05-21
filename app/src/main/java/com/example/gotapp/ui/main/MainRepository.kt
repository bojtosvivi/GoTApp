package com.example.gotapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.GoTCharacters
import com.example.gotapp.model.UIState
import com.example.gotapp.network.APIService
import com.example.gotapp.persistence.AppDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: APIService,
    private val dao: AppDao) {

    @WorkerThread
    fun getCharacters(): Flow<GoTCharacters> = dao.getCharactersFlow()

    @WorkerThread
    suspend fun reloadCharacters(state: MutableStateFlow<UIState>) {
        state.emit(UIState.Loading)
        kotlin.runCatching {
            service.getCharacters()
        }.fold(
            onSuccess = {
                dao.insertAll(it)
                state.emit(UIState.Loaded)},
            onFailure = {
                Log.e("something went wrong", it.message.orEmpty())
                val error = when (it) {
                    is IOException -> "Please check your internet connection"
                    else -> it.message ?: "unknown error"
                }
                state.emit(UIState.Failed(error))
            },
        )
    }

    @WorkerThread
    fun deleteCharacter(it: GoTCharacter) {
        dao.delete(it)
    }
}
