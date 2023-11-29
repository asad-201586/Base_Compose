package com.android.paging_3_compose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.paging_3_compose.db.entities.MovieEntity
import com.android.paging_3_compose.db.dao.MovieDao

@Database(entities = [MovieEntity::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}