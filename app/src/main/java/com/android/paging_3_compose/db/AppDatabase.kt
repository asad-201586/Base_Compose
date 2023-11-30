package com.android.paging_3_compose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.paging_3_compose.db.entities.MovieEntity
import com.android.paging_3_compose.db.dao.ProductDao
import com.android.paging_3_compose.db.entities.ProductEntity

@Database(entities = [MovieEntity::class,ProductEntity::class],version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}