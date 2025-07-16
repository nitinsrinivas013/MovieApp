package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController){
    MainContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavController , movieList : List<Movie> = getMovies()){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.inversePrimary
                ),
                title = {
                    Text("Goated Movies",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(12.dp)) {
            LazyColumn {
                items(movieList){
                    MovieRow(
                        movie = it,
                        titleColor = MaterialTheme.colorScheme.secondaryContainer,
                        onItemClick = { movie ->
                            navController.navigate(MovieScreens.DetailsScreen.name + "/$movie")
                        }
                    )
                }
            }
        }
    }
}

