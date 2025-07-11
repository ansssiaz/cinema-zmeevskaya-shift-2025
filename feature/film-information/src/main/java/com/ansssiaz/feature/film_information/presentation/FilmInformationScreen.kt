package com.ansssiaz.feature.film_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import coil.compose.AsyncImage
import com.ansssiaz.component.theme.BG_Content
import com.ansssiaz.component.theme.BG_Secondary
import com.ansssiaz.component.theme.Cinema_Hover_Primary
import com.ansssiaz.component.theme.Text_Content
import com.ansssiaz.component.theme.Text_Content_BW
import com.ansssiaz.component.theme.Text_Tertiary
import com.ansssiaz.component.ui_components.ratingBar.RatingBar
import com.ansssiaz.feature.film_information.R
import com.ansssiaz.feature.film_information.domain.Film
import com.ansssiaz.feature.film_information.domain.Hall
import com.ansssiaz.feature.film_information.domain.Schedule
import com.ansssiaz.feature.film_information.domain.Seance
import com.ansssiaz.feature.film_information.presentation.viewmodel.FilmViewModel
import com.ansssiaz.shared.film.domain.formatDateForScheduleTabs
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

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
            state.schedules?.let { schedules ->
                FilmContent(
                    film = film,
                    schedules = schedules,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }

    }
}

@Composable
fun FilmContent(
    film: Film,
    schedules: List<Schedule>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
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

        ExpandableFilmDescription(film.description)

        Spacer(modifier = Modifier.height(24.dp))

        val formattedFilmDates: Map<LocalDate, String> = schedules
            .associate { it.date to it.date.formatDateForScheduleTabs() }

        ScrollableFilmDatesTabs(formattedFilmDates, schedules)

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
fun ExpandableFilmDescription(text: String) {
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

@Composable
fun ScrollableFilmDatesTabs(dates: Map<LocalDate, String>, schedules: List<Schedule>) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val dateKeys = dates.keys.toList()
    val dateValues = dates.values.toList()

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        indicator = {},
        divider = {},
        modifier = Modifier.background(BG_Secondary)
    ) {
        dateValues.forEachIndexed { index, title ->
            val isSelected = selectedTabIndex == index
            Tab(
                selected = isSelected,
                onClick = { selectedTabIndex = index },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .background(
                        color = if (isSelected) Color.White else BG_Secondary,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clip(RoundedCornerShape(14.dp)),
                text = {
                    Text(
                        text = title,
                        color = if (isSelected) Text_Tertiary else Text_Content_BW
                    )
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    val selectedDate: LocalDate? = dateKeys.getOrNull(selectedTabIndex)

    val selectedSchedule = schedules.find { it.date == selectedDate }
    val seancesByHall: Map<Hall, List<Seance>> = selectedSchedule?.seances
        ?.groupBy { it.hall }
        ?: emptyMap()

    seancesByHall.forEach { (hall, seances) ->
        val hallNameRus = when (hall.name) {
            "Red" -> "Красный зал"
            "Blue" -> "Синий зал"
            "Green" -> "Зеленый зал"
            else -> hall.name
        }

        Text(
            text = hallNameRus,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        FlowRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            seances.forEach { seance ->
                SuggestionChip(
                    onClick = {  },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = BG_Secondary,
                        labelColor = Text_Content_BW
                    ),
                    label = { Text(text = seance.time) },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
