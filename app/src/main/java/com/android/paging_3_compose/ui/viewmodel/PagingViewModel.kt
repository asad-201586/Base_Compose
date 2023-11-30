package com.android.paging_3_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.paging_3_compose.db.entities.ProductEntity
import com.android.paging_3_compose.mappers.toProductItem
import com.android.paging_3_compose.paging_source.UsersPagingSource
import com.android.paging_3_compose.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val appRepo: AppRepo,
    pager: Pager<Int,ProductEntity>
): ViewModel() {

    fun getProducts() = Pager(PagingConfig(pageSize = 12)) {
        UsersPagingSource(appRepo)
    }
        .flow
        .cachedIn(viewModelScope)

    val productPagingFlow = pager
        .flow
        .map {
            it.map { productEntity ->
                productEntity.toProductItem()
            }
        }
        .cachedIn(viewModelScope)
}