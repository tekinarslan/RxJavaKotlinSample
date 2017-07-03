package com.tekinarslan.kotlinrxjavasample.service

import com.tekinarslan.kotlinrxjavasample.model.PhotosDataModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by selimtekinarslan on 6/29/2017.
 */
interface DataService {

    @GET("photos")
    fun getData(): Observable<ArrayList<PhotosDataModel>>
}