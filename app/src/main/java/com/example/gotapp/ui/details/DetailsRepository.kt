package com.example.gotapp.ui.details

import androidx.annotation.WorkerThread
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.GoTCharacters
import com.example.gotapp.network.APIService
import com.example.gotapp.persistence.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val dao: AppDao
) {
    @WorkerThread
    fun getCharacters(): Flow<GoTCharacters> = dao.getCharactersFlow()
}