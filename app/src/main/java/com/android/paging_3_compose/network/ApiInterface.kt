package com.android.paging_3_compose.network

import com.android.paging_3_compose.network.model.PostsDataResponse
import com.android.paging_3_compose.network.model.ProductResponse
import com.android.paging_3_compose.network.model.UserResponse
import retrofit2.http.*


interface ApiInterface {

    @GET(POST_LIST)
    suspend fun getPosts(): PostsDataResponse

    @GET("/v2/beers")
    suspend fun getProducts(
        @Query("per_page") limit: Int = 20,
        @Query("page") page: Int,
    ): ProductResponse
}

