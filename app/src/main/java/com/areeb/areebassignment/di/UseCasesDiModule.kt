package com.areeb.areebassignment.di

import com.areeb.areebassignment.domain.repository.MoviesRepository
import com.areeb.areebassignment.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesDiModule {

    @Singleton
    @Provides
    fun provideGetArticlesUseCase(
        articleRepository: MoviesRepository,
    ): GetMoviesUseCase {
        return GetMoviesUseCase(articleRepository)
    }
}



















