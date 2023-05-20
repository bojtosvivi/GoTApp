package com.example.gotapp.ui.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.gotapp.ui.header.Header

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Details (slug: String?, title: String?, viewModel: DetailsViewModel = hiltViewModel()) {
    val characters by viewModel.characters.collectAsState(initial = listOf())
    val pagerState = rememberPagerState(0)

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getCharactersOfHouse(slug)
    })
    Column() {
        Header(title = title.orEmpty(), isBackButtonVisible = true )
        HorizontalPager(pageCount = characters.size, state = pagerState, modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)) {
            val showQuotes = remember {
                mutableStateOf(false)
            }
            Card (
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(Color.White)

            ) {
                val animatedAlpha = animateFloatAsState(targetValue = if(showQuotes.value) 1f else 0f)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                    Image(painter = rememberImagePainter("https://www.usmagazine.com/wp-content/uploads/2022/12/Everything-to-Know-About-the-Jon-Snow-Series-HBO-Is-Developing-With-Game-of-Thrones-Kit-Harington-125.jpg?w=1331&quality=86&strip=all"),
                        contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight, alignment = Alignment.TopCenter
                    )
                    Text(characters?.get(it)?.name.orEmpty(),
                        modifier = Modifier.fillMaxWidth().background(Color.Black.copy(0.7f)).padding(16.dp),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopEnd)) {
                        Text(
                            text = characters?.get(it)?.quotes?.joinToString(separator = "\n\n") { it }.orEmpty(),
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    Color.Black.copy(animatedAlpha.value),
                                    RoundedCornerShape(16.dp)
                                )
                                .alpha(animatedAlpha.value)
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )
                        Button(
                            onClick = { showQuotes.value = !showQuotes.value },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(Color.Blue),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(60.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Icon(
                                Icons.Default.QuestionMark, contentDescription = "", tint = Color.White
                            )
                        }
                    }
                }
            }
        }

    }
}