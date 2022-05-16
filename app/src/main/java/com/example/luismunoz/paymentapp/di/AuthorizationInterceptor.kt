package com.example.luismunoz.paymentapp.di

import com.example.luismunoz.paymentapp.BuildConfig
import com.example.luismunoz.paymentapp.util.PUBLIC_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url()

        val newUrlRequest = httpUrl.newBuilder().addQueryParameter(PUBLIC_KEY, BuildConfig.API_KEY).build()
        val requestBuilder: Request.Builder = original.newBuilder().url(newUrlRequest)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}