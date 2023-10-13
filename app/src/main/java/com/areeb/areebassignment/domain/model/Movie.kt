package com.areeb.areebassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey
    var movieId: Int,
    val title: String,
    val overview: String,
    val backdropPathUrl: String,
    val posterPathUrl: String,
    val lang: String,
    val releaseDate: String,
    val voteAverage: Double,
)
