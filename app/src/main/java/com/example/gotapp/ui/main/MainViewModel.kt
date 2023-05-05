package com.example.gotapp.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gotapp.model.GoTCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    val characterList: Flow<GoTCharacters> =
        mainRepository.getCharacters(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError = { _error.value = it }
        )



    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    private val _error: MutableState<String?> = mutableStateOf(null)

    val isLoading: State<Boolean> get() = _isLoading
    val error: State<String?> get() = _error

}