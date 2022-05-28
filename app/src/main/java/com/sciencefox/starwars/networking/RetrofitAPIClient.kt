package com.sciencefox.starwars.networking

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIClient {

    fun getRetrofitClient() : NetworkService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

    fun getRetrofitClientForFilms(baseUrl: String) : NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideHttpClient())
            .build()
            .create(NetworkService::class.java)
    }

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
            val request = chain.request()
            Log.d("TAG", "provideHttpClient: Netwrok Request = $request")
            chain.proceed(request)
        }.build()
    }

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }
}