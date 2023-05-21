package com.example.gotapp.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.GoTCharacters
import com.example.gotapp.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val characterList: Flow<GoTCharacters> = mainRepository.getCharacters()

    val uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loaded)

    fun reloadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.reloadCharacters(uiState)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            characterList.collectLatest {
                if (it.isEmpty()) {
                    mainRepository.reloadCharacters(uiState)
                }
            }
        }
    }

}