package com.example.howzmyweather.api

import com.airbnb.lottie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(APP_ID, "705002d30fc80610a92d7d1231b4a14d")
            .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    companion object{
        const val APP_ID = "appid"
    }

}