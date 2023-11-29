package com.android.paging_3_compose.network.model


import com.google.gson.annotations.SerializedName

class PostsDataResponse : ArrayList<PostsDataResponse.PostsDataResponseItem>(){
    data class PostsDataResponseItem(
        @SerializedName("body")
        val body: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("userId")
        val userId: Int?
    )
}