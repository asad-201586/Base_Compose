package com.android.paging_3_compose.paging_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.paging_3_compose.network.model.ProductResponse
import com.android.paging_3_compose.repo.AppRepo
import com.google.gson.Gson

class UsersPagingSource(
    private val repo: AppRepo
) : PagingSource<Int, ProductResponse.ProductResponseItem>() {

    override suspend fun load(params: LoadParams<Int>):  LoadResult<Int, ProductResponse.ProductResponseItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            Log.d("paging_three", "load: page: $nextPageNumber")
            val response = repo.getProducts(page = nextPageNumber)
            Log.d("paging_three", "load: response: ${Gson().toJson(response)}")

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            Log.d("paging_three", "load: error -> ${e.localizedMessage}")
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