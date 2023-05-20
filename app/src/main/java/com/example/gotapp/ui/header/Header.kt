package com.example.gotapp.ui.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gotapp.ui.navigation.LocalNavController

@Composable
fun Header(title: String, isBackButtonVisible: Boolean, actions: @Composable RowScope.() -> Unit = {}) {
    val navController =  LocalNavController.current
    TopAppBar(
        title = {
            Text(title)
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = if (isBackButtonVisible) {
            {
                Icon(Icons.Default.ArrowBackIos, contentDescription = "Back", modifier = Modifier.clickable {
                    navController.navigateUp()
                })
            }
        } else null,
        actions = actions
    )
}