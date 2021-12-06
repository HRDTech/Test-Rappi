package com.solucioneshr.soft.testrappi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solucioneshr.soft.testrappi.data.ConfigApp
import com.solucioneshr.soft.testrappi.data.DataMovie
import com.solucioneshr.soft.testrappi.data.FunRepository

class FunViewModels(application: Application): AndroidViewModel(application) {
    private var funRepository: FunRepository? = null
    var getModelLiveData: LiveData<DataMovie>? = null
    var getPageTopRateModelLiveData: LiveData<DataMovie>? = null
    var getPagePopulateModelLiveData: LiveData<DataMovie>? = null
    var getPopulateModelLiveData: LiveData<DataMovie>? = null

    init {
        funRepository = FunRepository()
        getModelLiveData = MutableLiveData()
        getPageTopRateModelLiveData = MutableLiveData()
        getPagePopulateModelLiveData = MutableLiveData()
        getPopulateModelLiveData = MutableLiveData()
    }

    fun getMovieTopRate(){
        getModelLiveData = funRepository?.getMovie_TopRate()
    }

    fun getPageMovieTopRate(page: String){
        getPageTopRateModelLiveData = funRepository?.getMovie_TopRatePage(page)
    }
}