package com.android.paging_3_compose.network.model


import com.google.gson.annotations.SerializedName

class ProductResponse : ArrayList<ProductResponse.ProductResponseItem>(){
    data class ProductResponseItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("image_url")
        val imageUrl: String?
    )
}