package com.example.movietop10.movieApi

import com.example.movietop10.movieApi.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieAPI {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getMovieList(
        @Query("targetDt") targetDt: String?,
        @Query("key") key: String?
    ): Call<MovieResponse>
}