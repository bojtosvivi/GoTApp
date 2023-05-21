package com.example.gotapp.ui.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gotapp.themes.got_textColor
import com.example.gotapp.ui.navigation.LocalNavController

@Composable
fun Header(
    title: String,
    isBackButtonVisible: Boolean,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedVisibility(visibleState = MutableTransitionState(false).apply {
                this.targetState = isBackButtonVisible
            }) {
                Icon(
                    Icons.Default.ArrowBackIos,
                    contentDescription = "Back",
                    modifier = Modifier.size(34.dp).clickable {
                        navController.navigateUp()
                    },
                    tint = got_textColor
                )
            }
            Text(
                title,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = got_textColor
                ),
                modifier = Modifier
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            actions()
        }
    }
}
