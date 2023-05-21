package com.example.gotapp.ui.alertdialog

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.gotapp.themes.got_red
import com.example.gotapp.themes.got_textColor
import com.example.gotapp.themes.got_white

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
            }, colors = ButtonDefaults.buttonColors(got_red, got_white)) {
                Text(onConfirmTitle)
            }
        },
        title = {
            Text(title, style = MaterialTheme.typography.h6.copy(color = got_textColor))
        },
        text = {
            Text(text = message, style = MaterialTheme.typography.subtitle1.copy(color = got_textColor))
        },
        dismissButton = {
            Button(onClick = { showAlert.value = false }, colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary, got_white)) {
                Text("Bezárás")
            }
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    )
}