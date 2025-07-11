package com.ansssiaz.cinemaapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ansssiaz.feature.film_information.presentation.FilmInformationScreen
import com.ansssiaz.feature.list_of_films.presentation.ListOfFilmsScreen

fun NavGraphBuilder.filmsNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = "listOfFilmsScreen", route = "films") {
        composable("listOfFilmsScreen") {
            ListOfFilmsScreen(
                onDetailsClick = { film ->
                    navController.navigate("filmInformationScreen/${film.id}")
                }
            )
        }


        composable(
            "filmInformationScreen/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.LongType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getLong("filmId")
            if (filmId != null) {
                FilmInformationScreen(filmId = filmId)
            }
        }
    }
}