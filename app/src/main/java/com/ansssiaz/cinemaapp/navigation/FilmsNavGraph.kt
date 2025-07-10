package com.ansssiaz.cinemaapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ansssiaz.feature.list_of_films.presentation.ListOfFilmsScreen

fun NavGraphBuilder.filmsNavGraph(
    navController: NavHostController
) {
    navigation(startDestination = "listOfFilmsScreen", route = "films") {
        composable("listOfFilmsScreen") {
            ListOfFilmsScreen(
                onDetailsClick = { film ->
                    navController.navigate("filmInformationScreen")
                }
            )
        }

        composable("filmInformationScreen") {
        }
    }
}