package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.model.Movie
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle


@Composable
fun MovieRow(movie: Movie,
             onItemClick : (String) -> Unit = {},
             titleColor: Color = MaterialTheme.colorScheme.secondaryContainer ){


    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.padding(4.dp).fillMaxWidth()
        .clickable{
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(CornerSize(16.dp)),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Surface(modifier = Modifier.padding(12.dp).size(100.dp),
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    tonalElevation = 4.dp
                ){

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.images[0])
                            .crossfade(true)
                            .crossfade(1000)
                            .build(),
                        contentDescription = "Movie Poster",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(modifier = Modifier.padding(4.dp).weight(1f)){
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = titleColor,
                        fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Director: ${movie.director}",
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Spacer(modifier = Modifier.height(1.5.dp))
                    Text(
                        text = "Released: ${movie.year}",
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = if (!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Toggle Details",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.White
                )
            }
            AnimatedVisibility(expanded) {
                Column{
                    Text(buildAnnotatedString {
                        withStyle(SpanStyle(color = titleColor,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize)){
                            append("Plot: ")
                        }
                        withStyle(SpanStyle(color = Color.White ,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = FontWeight.SemiBold)){
                            append(movie.plot)
                        }
                    },
                        modifier = Modifier.padding(6.dp))
                    HorizontalDivider(modifier = Modifier.padding(3.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = titleColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Director: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(movie.director)
                            }
                        },
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(6.dp)
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = titleColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Actors: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(movie.actors)
                            }
                        },
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(6.dp)
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = titleColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("IMDB Rating: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(movie.rating)
                            }
                        },
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
    }
}