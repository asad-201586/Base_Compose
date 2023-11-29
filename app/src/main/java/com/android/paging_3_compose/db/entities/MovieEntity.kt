package com.android.paging_3_compose.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "movie_name") var movieName: String = "",
    @ColumnInfo(name = "imdb_rating") var imdbRating: Float = 0f,
    @ColumnInfo(name = "poster") var poster: String = "",
)
