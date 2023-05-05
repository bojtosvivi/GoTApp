package com.example.gotapp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Main (viewModel: MainViewModel = hiltViewModel()){
    val characterList by viewModel.characterList.collectAsState(initial = listOf())
    val isLoading by viewModel.isLoading
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if(isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(content = {
                items(characterList){
                    Text(text = it.name)
                }
            })
        }
    }
}