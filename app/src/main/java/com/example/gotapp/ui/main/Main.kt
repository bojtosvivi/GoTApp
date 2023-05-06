package com.example.gotapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gotapp.R
import com.example.gotapp.model.UIState

@Composable
fun Main(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UIState.Loading)
    val characters by viewModel.characterList.collectAsState(initial = listOf())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(modifier = Modifier.weight(1f),columns = GridCells.Fixed(2), content = {
            items(characters) {
                Column(modifier = Modifier
                    .height(160.dp)
                    .clickable {
                        viewModel.deleteCharacter(it)
                    }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24), contentDescription = "" , modifier = Modifier.weight(1f))
                    Text(text = it.name, modifier = Modifier)
                }
            }
        })
        Column(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val result = uiState) {
                is UIState.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }

                is UIState.Loaded -> {
                    Text(text = "Adatbázis frissítve", color = Color.White)
                    OutlinedButton(onClick = { viewModel.reloadCharacters() }) {
                        Text(text = "Frissítés")
                    }
                }

                is UIState.Failed -> {
                    Text(text = result.message, color = Color.White)
                    OutlinedButton(onClick = { viewModel.reloadCharacters() }) {
                        Text(text = "Frissítés")
                    }
                }
            }
        }
    }
}