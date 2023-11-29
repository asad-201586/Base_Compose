package com.android.paging_3_compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.paging_3_compose.R
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252525))
    ) {
        val users = viewModel.getProducts().collectAsLazyPagingItems()
        LazyColumn {
            items(users.itemCount) { index ->
                users[index]?.let {
                    ProductItem(it)
                }
            }

            when (users.loadState.append) {
                is LoadState.Error -> {
                    item {

                    }
                }
                LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Center
    )
    {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .height(42.dp)
                .height(42.dp),
            strokeWidth = 3.dp
        )
    }
}

@Preview
@Composable
fun ProductItem(item: ProductResponse.ProductResponseItem? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 12.dp)
            .background(Color.Black)
    ) {
        //Text(text = item?.name?:"Item One Two", color = Color.White)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadImage(
                imageUrl = item?.imageUrl ?: "",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .padding(16.dp)
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = item?.name ?: "name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = item?.description ?: "description",
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }

    }
}

@Composable
fun LoadImage(imageUrl: String? = null, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = "image_",
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape)
    )
}
