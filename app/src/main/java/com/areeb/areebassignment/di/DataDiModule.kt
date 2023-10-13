package com.areeb.areebassignment.di

import android.content.Context
import com.areeb.areebassignment.data.local.MovieDao
import com.areeb.areebassignment.data.local.MoviesDatabase
import com.areeb.areebassignment.data.network.MovieApi
import com.areeb.areebassignment.data.network.dto.movie.MovieDtoMapper
import com.areeb.areebassignment.domain.repository.MoviesRepository
import com.stc.stcassignment.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {

    @Singleton
    @Provides
    fun provideMoviesDtoMapper(): MovieDtoMapper {
        return MovieDtoMapper()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        MoviesDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideArticlesDao(db: MoviesDatabase) = db.getMoviesDao()

    @Singleton
    @Provides
    fun provideArticleRepository(
        api: MovieApi,
        movieDtoMapper: MovieDtoMapper,
        localSource: MovieDao,
    ) = MovieRepositoryImpl(api, movieDtoMapper, localSource) as MoviesRepository

}



















