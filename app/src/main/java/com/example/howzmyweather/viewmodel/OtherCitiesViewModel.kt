package com.example.howzmyweather.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.howzmyweather.api.WeatherAPIClient
import com.example.howzmyweather.model.WeatherResponse
import com.example.howzmyweather.util.Constant.METRIC
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class OtherCitiesViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = WeatherAPIClient()
    private val disposable = CompositeDisposable()

    val cityData = MutableLiveData<WeatherResponse>()
    val cityDataLoading = MutableLiveData<Boolean>()

    fun getWeatherByCityName(cityName: String) {
        cityDataLoading.value = true
        disposable.add(
            apiClient.getDataUsingCityName(cityName, METRIC)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {
                    override fun onSuccess(t: WeatherResponse) {
                        cityData.value = t
                        cityDataLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Error : ", e.message + " " + e.printStackTrace())

                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}