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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gotapp.R
import com.example.gotapp.model.House
import com.example.gotapp.model.UIState
import com.example.gotapp.ui.alertdialog.AppDialog
import com.example.gotapp.ui.header.Header
import com.example.gotapp.ui.navigation.LocalNavController

@Composable
fun Main(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UIState.Loading)
    val characters by viewModel.characterList.collectAsState(initial = listOf())
    val navController = LocalNavController.current
    val showAlert = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Header(title = "Houses", isBackButtonVisible = false) {
            when (val result = uiState) {
                is UIState.Loading -> {
                    CircularProgressIndicator(color = Color.White)
                }

                is UIState.Loaded -> {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier.clickable {
                            viewModel.reloadCharacters()
                        })
                }

                is UIState.Failed -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Bug",
                            modifier = Modifier.clickable {
                                showAlert.value = true
                            })

                        if (showAlert.value) {
                            AppDialog(
                                showAlert = showAlert,
                                message = result.message,
                                title = "Hiba",
                                onConfirmTitle = "Frissítés"
                            ) {
                                viewModel.reloadCharacters()
                            }
                        }
                    }
                }
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            items(characters.groupBy { it.house ?: House("unknown", "unknown") }.toList()) {
                Column(
                    modifier = Modifier
                        .height(160.dp)
                        .clickable {
                            navController.navigate("details_page/${it.first.slug}/${it.first.name}") {
                                launchSingleTop = true
                            }
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_supervised_user_circle_24),
                        contentDescription = "",
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = it.first.name, modifier = Modifier)
                }
            }
        }
    }
}