package com.android.paging_3_compose.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.paging_3_compose.network.model.ProductResponse
import com.android.paging_3_compose.repo.AppRepo

class UsersPagingSource(
    private val repo: AppRepo
) : PagingSource<Int, ProductResponse.ProductResponseItem>() {

    override suspend fun load(params: LoadParams<Int>):  LoadResult<Int, ProductResponse.ProductResponseItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getProducts(page = nextPageNumber)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductResponse.ProductResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}