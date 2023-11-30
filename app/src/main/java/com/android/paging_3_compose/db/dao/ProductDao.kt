package com.android.paging_3_compose.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.android.paging_3_compose.db.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun pagingSource(): PagingSource<Int,ProductEntity>

    @Upsert
    suspend fun upsertAll(products: List<ProductEntity>)

    @Query("delete from products")
    suspend fun clearAllProducts()


}
