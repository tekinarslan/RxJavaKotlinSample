package com.tekinarslan.kotlinrxjavasample.model

import com.google.gson.annotations.SerializedName

/**
 * Created by selimtekinarslan on 6/29/2017.
 */
class PhotosDataModel {

    var title: String? = null
    @SerializedName("url")
    var subTitle: String? = null
    var thumbnailUrl: String? = null
}