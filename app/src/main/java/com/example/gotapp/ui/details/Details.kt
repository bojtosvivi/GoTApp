package com.example.gotapp.ui.details

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.gotapp.R
import com.example.gotapp.themes.got_black
import com.example.gotapp.themes.got_textColor
import com.example.gotapp.themes.got_white
import com.example.gotapp.ui.header.Header

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Details(slug: String?, title: String?, viewModel: DetailsViewModel = hiltViewModel()) {
    val characters by viewModel.characters.collectAsState(initial = listOf())
    val pagerState = rememberPagerState(0)

    LaunchedEffect(Unit) {
        viewModel.getCharactersOfHouse(slug)
    }
    Column {
        Header(title = title.orEmpty(), isBackButtonVisible = true)
        if (characters.isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            }
        } else {
            HorizontalPager(
                pageCount = characters.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                val showQuotes = remember {
                    mutableStateOf(false)
                }
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White)

                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(
                            painter = rememberImagePainter(data = characters[it].getImage()) {
                                this.error(R.drawable.crest_placeholder)
                                this.placeholder(R.drawable.crest_placeholder)
                            },
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillHeight,
                            alignment = Alignment.TopCenter
                        )
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                        ) {

                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                AnimatedVisibility(
                                    visible = showQuotes.value,
                                    modifier = Modifier.weight(1f, false),
                                    enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                                ) {
                                    LazyColumn(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp)
                                            .fillMaxWidth()
                                            .background(
                                                if (isSystemInDarkTheme())
                                                    got_black.copy(0.8f)
                                                else got_white.copy(0.8f),
                                                RoundedCornerShape(16.dp)
                                            ),
                                        contentPadding = PaddingValues(8.dp)
                                    ) {
                                        itemsIndexed(characters[it].quotes) { index, item ->
                                            Text(
                                                text = item,
                                                style = MaterialTheme.typography.h5.copy(color = got_textColor),
                                                textAlign = TextAlign.Start
                                            )
                                            if (index != characters[it].quotes.lastIndex)
                                                Divider(
                                                    modifier = Modifier.padding(vertical = 4.dp),
                                                    color = MaterialTheme.colors.secondary
                                                )
                                        }
                                    }
                                }
                                IconButton(
                                    onClick = { showQuotes.value = !showQuotes.value },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(60.dp)
                                        .background(
                                            color = if (isSystemInDarkTheme())
                                                got_black.copy(0.7f)
                                            else got_white.copy(0.7f),
                                            shape = CircleShape
                                        )
                                        .padding(16.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.quotation),
                                        contentDescription = "quotes",
                                        colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) got_white else got_black),
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                            Text(
                                characters[it].name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (isSystemInDarkTheme()) got_black.copy(0.7f) else got_white.copy(
                                            0.7f
                                        )
                                    )
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h4.copy(color = got_textColor)
                            )
                        }

                    }
                }
            }
        }
    }
}