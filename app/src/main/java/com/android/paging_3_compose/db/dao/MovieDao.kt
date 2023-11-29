package com.android.paging_3_compose.db.dao

import androidx.room.*
import com.android.paging_3_compose.db.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllMovie(): List<MovieEntity>?

    @Insert
    fun addMovie(user: MovieEntity?)


}