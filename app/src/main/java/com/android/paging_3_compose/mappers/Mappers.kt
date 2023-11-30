package com.android.paging_3_compose.mappers

import com.android.paging_3_compose.db.entities.ProductEntity
import com.android.paging_3_compose.network.model.ProductResponse

fun ProductResponse.ProductResponseItem.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name?:"",
        description = description?:"",
        imageUrl = imageUrl
    )
}

fun ProductEntity.toProductItem(): ProductResponse.ProductResponseItem {
    return ProductResponse.ProductResponseItem(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}