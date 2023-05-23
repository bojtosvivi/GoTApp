package com.example.gotapp.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotapp.model.GoTCharacters
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repository: DetailsRepository): ViewModel() {
    private val _characters: MutableStateFlow<GoTCharacters> = MutableStateFlow(listOf())
    val characters: Flow<GoTCharacters> get() = _characters

    fun getCharactersOfHouse(slug: String?) {
        viewModelScope.launch(Dispatchers.IO){
            repository.getCharacters().collectLatest {
                Firebase.analytics.logEvent("GOT_HOUSE", Bundle().apply {
                    putString(FirebaseAnalytics.Param.ITEM_ID, it.firstOrNull()?.house?.slug);
                    putString(FirebaseAnalytics.Param.ITEM_NAME, it.firstOrNull()?.house?.name);
                })
                _characters.emit(it.filter { character -> character.house?.slug == slug })
            }
        }
    }

}