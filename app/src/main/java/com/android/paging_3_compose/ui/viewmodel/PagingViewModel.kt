package com.android.paging_3_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.android.paging_3_compose.paging_source.UsersPagingSource
import com.android.paging_3_compose.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val appRepo: AppRepo
): ViewModel() {

    fun getProducts() = Pager(PagingConfig(pageSize = 12)) {
        UsersPagingSource(appRepo)
    }
        .flow
        .cachedIn(viewModelScope)
}