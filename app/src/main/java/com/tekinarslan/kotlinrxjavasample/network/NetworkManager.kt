package com.tekinarslan.kotlinrxjavasample.network

import com.google.gson.GsonBuilder
import com.tekinarslan.kotlinrxjavasample.service.DataService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by selimtekinarslan on 6/29/2017.
 */
class NetworkManager {

    lateinit var service: DataService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(OkHttpClient())
                .build()
        createServices(retrofit)
    }

    fun createServices(retrofit: Retrofit) {
        service = retrofit.create(DataService::class.java)
    }
}