package com.android.paging_3_compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.paging_3_compose.network.model.ProductResponse
import com.android.paging_3_compose.ui.viewmodel.PagingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingActivity : ComponentActivity() {

    private val viewModel by viewModels<PagingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingScreen(viewModel)
        }
    }
}

@Composable
fun PagingScreen(viewModel: PagingViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        val users = viewModel.getProducts().collectAsLazyPagingItems()
        LazyColumn {
            items(users.itemCount) { index: Int ->
                users[index]?.let {
                    UserItem(it)
                }

            }
        }
    }
}

@Preview
@Composable
fun UserItem(item: ProductResponse.ProductResponseItem? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(50.dp)
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = item?.name?:"Item One", color = Color.White)
    }
}
