package com.android.paging_3_compose.remote_mediator

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.android.paging_3_compose.db.AppDatabase
import com.android.paging_3_compose.db.entities.ProductEntity
import com.android.paging_3_compose.mappers.toProductEntity
import com.android.paging_3_compose.network.ApiInterface
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val api: ApiInterface,
    private val db: AppDatabase
): RemoteMediator<Int,ProductEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else (lastItem.id / state.config.pageSize) + 1
                }
            }

            val products = api.getProducts(page = loadKey)

            if (loadType == LoadType.REFRESH) db.productDao().clearAllProducts()

            val productsEntities = products.map { it.toProductEntity() }
            db.productDao().upsertAll(productsEntities)

            MediatorResult.Success(
                endOfPaginationReached = products.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}