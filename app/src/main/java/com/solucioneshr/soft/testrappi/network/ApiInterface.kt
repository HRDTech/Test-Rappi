package com.solucioneshr.soft.testrappi.network

import com.solucioneshr.soft.testrappi.data.ConfigApp
import retrofit2.Call
import com.solucioneshr.soft.testrappi.data.DataMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    //-----Interfaces Movie----------------------------------

    @GET("movie/top_rated?api_key=" + ConfigApp.API_KEY)
    fun getMovieTopRate(): Call<DataMovie>

    @GET("movie/top_rated?api_key=" + ConfigApp.API_KEY + "&")
    fun getMovieTopRatePages(@Query("page") page: String?): Call<DataMovie>

    @GET("movie/popular?api_key=" + ConfigApp.API_KEY)
    fun getMoviePopular(): Call<DataMovie>

    @GET("movie/popular?api_key=" + ConfigApp.API_KEY + "&")
    fun getMoviePopularPages(@Query("page") page: String?): Call<DataMovie>
    //========================================================

    //-----Interfaces Tv----------------------------------

    @GET("tv/top_rated?api_key=" + ConfigApp.API_KEY)
    fun getTvTopRate(): Call<DataMovie>

    @GET("tv/top_rated?api_key=" + ConfigApp.API_KEY  + "&")
    fun getTvTopRatePage(@Query("page") page: String?): Call<DataMovie>

    @GET("movie/popular?api_key=" + ConfigApp.API_KEY)
    fun getTvPopular(): Call<DataMovie>

    @GET("tv/top_rated?api_key=" + ConfigApp.API_KEY  + "&")
    fun getTvPopularPage(@Query("page") page: String?): Call<DataMovie>
    //========================================================
}