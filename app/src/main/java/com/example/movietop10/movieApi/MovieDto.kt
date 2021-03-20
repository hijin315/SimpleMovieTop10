package com.example.movietop10.movieApi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDto(
    @SerializedName("movieNm")
    var movieNm: String?,
    @SerializedName("salesAmt")
    var salesAmt: String?
) : Serializable {

}