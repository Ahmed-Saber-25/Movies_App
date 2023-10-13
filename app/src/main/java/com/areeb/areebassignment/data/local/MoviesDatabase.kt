package com.areeb.areebassignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.areeb.areebassignment.domain.model.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, MoviesDatabase::class.java, "movies_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}