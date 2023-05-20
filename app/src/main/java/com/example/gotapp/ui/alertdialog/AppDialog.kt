package com.example.gotapp.ui.alertdialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AppDialog(
    showAlert: MutableState<Boolean>,
    message: String,
    title: String,
    onConfirmTitle: String,
    onConfirm: () -> Unit
){
    AlertDialog(
        onDismissRequest = { showAlert.value = false },
        confirmButton = {
            Button({
                onConfirm()
                showAlert.value = false
            }) {
                Text(onConfirmTitle)
            }
        },
        title = {
            Text(title)
        },
        text = {
            Text(text = message, fontSize = 16.sp)
        },
        dismissButton = {
            Button(onClick = { showAlert.value = false }) {
                Text("Bezárás")
            }
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    )
}