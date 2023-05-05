package com.example.gotapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.gotapp.model.GoTCharacters
import com.example.gotapp.network.APIService
import com.example.gotapp.persistence.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: APIService,
    private val dao: AppDao) {

    @WorkerThread
    fun getCharacters(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var characters: GoTCharacters = dao.getAll()
        if (characters.isEmpty()) {
               try {
                   // request API network call asynchronously.
                   characters = service.getCharacters()
                   // handle the case when the API request gets a success response.
                   dao.insertAll(characters)
                   emit(characters)
               } catch(error: Exception) {
                   Log.e("something went wrong", error.message.orEmpty())

                   // handle the case when the API request is fails.
                   // e.g. internal server error.
                   onError(error.message.orEmpty())
                   emit(listOf())
               }
        } else {
            emit(characters)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}
