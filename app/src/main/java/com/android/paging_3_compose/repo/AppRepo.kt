package com.android.paging_3_compose.repo

import com.android.paging_3_compose.db.AppDatabase
import com.android.paging_3_compose.db.entities.MovieEntity
import com.android.paging_3_compose.network.ApiInterface
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val apiHitter: ApiInterface,
    private val db: AppDatabase
) {
    suspend fun getPosts() = apiHitter.getPosts()
    suspend fun getProducts(page: Int) = apiHitter.getProducts(page = page)





    /**
     * ROOM DATABASE
     */
    fun getMovies() = db.movieDao().getAllMovie()

    fun addMovie(entity: MovieEntity) {
        db.movieDao().addMovie(entity)
    }

}