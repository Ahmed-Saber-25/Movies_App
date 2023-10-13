package com.areeb.areebassignment.data.local

import androidx.room.*
import com.areeb.areebassignment.domain.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articles: List<Movie>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("Delete FROM movies")
    suspend fun deleteAllMovies()
}