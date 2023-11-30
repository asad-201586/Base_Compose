package com.android.paging_3_compose.repo

import com.android.paging_3_compose.db.AppDatabase
import com.android.paging_3_compose.db.entities.ProductEntity
import com.android.paging_3_compose.network.ApiInterface
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val apiHitter: ApiInterface,
    private val db: AppDatabase
) {
    suspend fun getPosts() = apiHitter.getPosts()
    suspend fun getProducts(page: Int) = apiHitter.getProducts(page = page)





    /**
     * ROOM DATABASE
     */

    suspend fun upsertAll(product: List<ProductEntity>) = db.productDao().upsertAll(product)
    suspend fun clearAllProducts() = db.productDao().clearAllProducts()
    suspend fun pagingSource() = db.productDao().pagingSource()
}