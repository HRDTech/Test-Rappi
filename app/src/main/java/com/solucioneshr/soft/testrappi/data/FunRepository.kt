package com.solucioneshr.soft.testrappi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solucioneshr.soft.testrappi.network.ApiClient
import com.solucioneshr.soft.testrappi.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FunRepository {
    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun getMovie_TopRate (): LiveData<DataMovie>{
        val dataResponse = MutableLiveData<DataMovie>()

        apiInterface?.getMovieTopRate()?.enqueue(object : Callback<DataMovie>{

            override fun onResponse(call: Call<DataMovie>, response: Response<DataMovie>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    dataResponse.value = res
                }else{
                    dataResponse.value = null
                }
            }

            override fun onFailure(call: Call<DataMovie>, t: Throwable) {
                dataResponse.value = null
            }

        })
        return dataResponse
    }

    fun getMovie_TopRatePage (page: String?): LiveData<DataMovie>{
        val dataResponse = MutableLiveData<DataMovie>()

        apiInterface?.getMovieTopRatePages(page)?.enqueue(object : Callback<DataMovie>{
            override fun onResponse(call: Call<DataMovie>, response: Response<DataMovie>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    dataResponse.value = res
                }else{
                    dataResponse.value = null
                }
            }

            override fun onFailure(call: Call<DataMovie>, t: Throwable) {
                dataResponse.value = null
            }

        })

        return dataResponse
    }
}