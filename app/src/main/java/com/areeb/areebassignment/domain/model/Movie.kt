package com.areeb.areebassignment.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "movies"
)
@Parcelize
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
):Parcelable
