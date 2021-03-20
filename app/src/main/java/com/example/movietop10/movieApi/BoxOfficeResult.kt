package com.example.movietop10.movieApi

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeList: List<MovieDto> = arrayListOf()
)