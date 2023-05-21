package com.example.gotapp.di

import com.example.gotapp.network.APIService
import com.example.gotapp.persistence.AppDao
import com.example.gotapp.ui.details.DetailsRepository
import com.example.gotapp.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepository(
        gotService: APIService,
        gotDao: AppDao
    ): MainRepository {
        return MainRepository(gotService, gotDao)
    }

    @Provides
    fun provideDetailsRepository(
        gotDao: AppDao
    ): DetailsRepository {
        return DetailsRepository(gotDao)
    }
}
