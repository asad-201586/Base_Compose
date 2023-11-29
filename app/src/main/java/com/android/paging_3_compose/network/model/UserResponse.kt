package com.android.paging_3_compose.network.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total")
    val total: Int?
) {
    data class Data(
        @SerializedName("firstName")
        val firstName: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("lastName")
        val lastName: String?,
        @SerializedName("picture")
        val picture: String?,
        @SerializedName("title")
        val title: String?
    )
}