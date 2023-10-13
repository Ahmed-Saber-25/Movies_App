package com.areeb.areebassignment.data.network.response


import com.google.gson.annotations.SerializedName
import com.areeb.areebassignment.data.network.dto.movie.MovieDto

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)