package com.android.paging_3_compose.network.model


import com.google.gson.annotations.SerializedName

class ProductResponse : ArrayList<ProductResponse.ProductResponseItem>(){
    data class ProductResponseItem(
        @SerializedName("description")
        val description: String?,
        @SerializedName("first_brewed")
        val firstBrewed: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image_url")
        val imageUrl: String?,
        @SerializedName("name")
        val name: String?
    )
}