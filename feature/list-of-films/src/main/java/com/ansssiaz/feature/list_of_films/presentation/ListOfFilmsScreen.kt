package com.ansssiaz.feature.list_of_films.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ansssiaz.component.theme.BG_Content
import com.ansssiaz.component.theme.BG_Secondary
import com.ansssiaz.component.theme.Cinema_Hover_Primary
import com.ansssiaz.component.theme.Text_Content
import com.ansssiaz.component.ui_components.ratingBar.RatingBar
import com.ansssiaz.feature.list_of_films.domain.Film
import com.ansssiaz.feature.list_of_films.presentation.viewmodel.FilmsViewModel
import com.ansssiaz.feature.list_of_films.util.getErrorText
import com.ansssiaz.list_of_films.R
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfFilmsScreen(
    onDetailsClick: (Film) -> Unit,
) {
    val filmsViewModel: FilmsViewModel = koinViewModel()
    val state by filmsViewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val action = stringResource(R.string.snackbar_action)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.poster),
                        style = androidx.compose.ui.text.TextStyle(
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
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LaunchedEffect(state.isError) {
            if (state.isError) {
                val errorText =
                    state.status.throwableOrNull?.getErrorText(context).toString()

                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = errorText,
                        actionLabel = action,
                        duration = SnackbarDuration.Indefinite
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        filmsViewModel.getFilms()
                    }
                }
            }
        }

        when {
            state.isLoading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                FilmsList(
                    films = state.films,
                    onDetailsClick = onDetailsClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun FilmsList(
    films: List<Film>?,
    modifier: Modifier = Modifier,
    onDetailsClick: (Film) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(films ?: emptyList()) { film ->
            FilmItem(film = film, onDetailsClick = { onDetailsClick(film) })
        }
    }
}

@Composable
fun FilmItem(
    film: Film,
    onDetailsClick: (filmId: Long) -> Unit
) {
    Column(
        modifier = Modifier
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
                        style = androidx.compose.ui.text.TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp,
                            color = BG_Content
                        )
                    )
                    Text(
                        text = "${film.country}, ${film.releaseYear}",
                        style = androidx.compose.ui.text.TextStyle(
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
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Фильм",
                style = androidx.compose.ui.text.TextStyle(
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
                style = androidx.compose.ui.text.TextStyle(
                    color = Text_Content,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onDetailsClick(film.id) },
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
                text = stringResource(R.string.more_details),
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}