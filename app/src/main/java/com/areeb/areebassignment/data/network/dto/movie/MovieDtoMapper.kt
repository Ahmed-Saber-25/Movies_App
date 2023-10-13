package com.areeb.areebassignment.data.network.dto.movie

import com.areeb.areebassignment.domain.model.Movie
import com.areeb.areebassignment.domain.util.Constants
import com.areeb.areebassignment.domain.util.DomainMapper

class MovieDtoMapper : DomainMapper<MovieDto, Movie> {

    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            movieId = model.id,
            title = model.originalTitle,
            overview = model.overview,
            backdropPathUrl = Constants.POSTER_BASE_URL + model.backdropPath,
            posterPathUrl = Constants.POSTER_BASE_URL + model.posterPath,
            lang = model.originalLanguage,
            releaseDate = model.releaseDate,
            voteAverage = model.voteAverage
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        return MovieDto(
            id=domainModel.movieId,
            originalTitle=domainModel.title,
            overview=domainModel.overview,
            posterPath=domainModel.posterPathUrl,
            backdropPath=domainModel.backdropPathUrl,
            originalLanguage=domainModel.lang,
            releaseDate=domainModel.releaseDate,
            voteAverage=domainModel.voteAverage
        )
    }

    fun toDomainList(initial: List<MovieDto>): List<Movie> {
        return initial.map { mapToDomainModel(it) }
    }

}