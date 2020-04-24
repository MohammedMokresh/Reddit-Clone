package com.mokresh.redditclone.di

import com.mokresh.redditclone.app.ApiServices
import com.mokresh.redditclone.app.AppDatabase
import com.mokresh.redditclone.data.ListingRepository
import com.mokresh.redditclone.ui.ListingViewModel
import com.mokresh.redditclone.utils.BaseSchedulers
import com.mokresh.redditclone.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val cacheModule by lazy {

    module {
        single {
            AppDatabase.getInstance(get()).listingDao()
        }
    }
}


val repositoryModule by lazy {
    module {

        single<ListingRepository> {
            ListingRepository.ListingRepositoryImpl(get(), get())
        }
    }
}

val appModule by lazy {
    module {
        single<BaseSchedulers> { BaseSchedulers.BaseSchedulersImpl() }
    }
}


val viewModelModule by lazy {
    module {
        viewModel {
            ListingViewModel(get())
        }
    }
}


val serviceModule by lazy {

    module {
        single<ApiServices> { ApiServices.Network(get(), get()) }
        single<Retrofit> {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}