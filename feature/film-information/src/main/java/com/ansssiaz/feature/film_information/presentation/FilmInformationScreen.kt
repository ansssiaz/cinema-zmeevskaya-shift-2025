package com.ansssiaz.feature.film_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ansssiaz.component.theme.BG_Content
import com.ansssiaz.component.theme.BG_Secondary
import com.ansssiaz.component.theme.Cinema_Hover_Primary
import com.ansssiaz.component.theme.Text_Content
import com.ansssiaz.component.ui_components.ratingBar.RatingBar
import com.ansssiaz.feature.film_information.R
import com.ansssiaz.feature.film_information.domain.Film
import com.ansssiaz.feature.film_information.presentation.viewmodel.FilmViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmInformationScreen(
    filmId: Long
) {
    val filmViewModel: FilmViewModel = koinViewModel(parameters = { parametersOf(filmId) })
    val state by filmViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.about_film),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            letterSpacing = 0.sp
                        )
                    )
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        state.film?.let { film ->
            FilmContent(
                film = film,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun FilmContent(
    film: Film,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(328f / 300f)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = film.image,
                contentDescription = stringResource(R.string.film_image_description),
                placeholder = ColorPainter(MaterialTheme.colorScheme.background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(
                        color = BG_Secondary,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 0.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column {
                    Text(
                        text = film.genres.first(),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp,
                            color = BG_Content
                        )
                    )
                    Text(
                        text = "${film.country}, ${film.releaseYear}",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp,
                            color = BG_Content
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "${film.name} (${film.ageRating})",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Фильм",
                style = TextStyle(
                    color = Text_Content,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            RatingBar(rating = film.kinopoiskRating)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Kinopoisk - ${film.kinopoiskRating}",
                style = TextStyle(
                    color = Text_Content,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        ExpandableText(film.description)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cinema_Hover_Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = stringResource(R.string.continue_button),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}

@Composable
fun ExpandableText(text: String) {
    var expanded by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val textToShow = if (expanded) text else text

    Column {
        Text(
            text = textToShow,
            maxLines = if (expanded) Int.MAX_VALUE else 6,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Text_Content,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            ),
            onTextLayout = { textLayoutResult = it }
        )

        if (!expanded && textLayoutResult?.hasVisualOverflow == true) {
            Text(
                text = "раскрыть",
                color = Text_Content,
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(top = 4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}