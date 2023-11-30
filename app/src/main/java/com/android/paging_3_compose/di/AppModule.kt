package com.android.paging_3_compose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.android.paging_3_compose.db.AppDatabase
import com.android.paging_3_compose.db.entities.ProductEntity
import com.android.paging_3_compose.network.ApiClient
import com.android.paging_3_compose.network.ApiInterface
import com.android.paging_3_compose.remote_mediator.ProductRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return ApiClient.getRetrofit()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"AMBER_IT_DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePager(api: ApiInterface,db: AppDatabase): Pager<Int,ProductEntity> {
        return Pager(
            config = PagingConfig(pageSize = 12),
            remoteMediator = ProductRemoteMediator(
                api = api,
                db = db
            ),
            pagingSourceFactory = {
                db.productDao().pagingSource()
            }
        )
    }
}