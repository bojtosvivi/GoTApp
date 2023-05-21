package com.example.gotapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gotapp.model.House
import com.example.gotapp.model.UIState
import com.example.gotapp.themes.got_black
import com.example.gotapp.themes.got_textColor
import com.example.gotapp.themes.got_white
import com.example.gotapp.ui.alertdialog.AppDialog
import com.example.gotapp.ui.header.Header
import com.example.gotapp.ui.navigation.LocalNavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Main(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState(initial = UIState.Loading)
    val houses = viewModel.characterList.collectAsState(initial = listOf()).value.groupBy {
        it.house ?: House("unknown", "unknown")
    }.toList()
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
                    CircularProgressIndicator(
                        color = got_textColor,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                    )
                }
                is UIState.Loaded -> {
                    //TODO: Ezt az ikon ki lehetne vinni egy külön @Composable fun-ba icon: ImageVector, tint: Color, onClick: () -> Unit paraméterekkel és fel lehet használni a 77. sorban is
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                viewModel.reloadCharacters()
                            },
                        tint = got_textColor
                    )
                }
                is UIState.Failed -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Bug",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    showAlert.value = true
                                },
                            tint = MaterialTheme.colors.error
                        )

                        if (showAlert.value) {
                            AppDialog(
                                showAlert = showAlert,
                                message = result.message,
                                title = "Error",
                                onConfirmTitle = "Reload"
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
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(houses) { (house, members) ->
                //TODO: Ezt a Cardot ki lehetne vinni egy külön @Composable funba egy house bementi paraméterrel
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(170f / 186f),
                    backgroundColor = MaterialTheme.colors.onBackground,
                    onClick = {
                        navController.navigate("details_page/${house.slug}/${house.name}") {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Image(
                            painter = painterResource(id = house.getCrest()),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .weight(1f)
                                .background(Color.Transparent),
                            contentScale = ContentScale.Fit,
                        )
                        Text(
                            text = house.name,
                            style = MaterialTheme.typography.h6.copy(color = got_textColor),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isSystemInDarkTheme()) got_black.copy(0.7f) else got_white.copy(
                                        0.7f
                                    )
                                )
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}