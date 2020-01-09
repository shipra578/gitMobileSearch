package com.shipra.android.gitmobilesearch.api

import com.shipra.android.gitmobilesearch.BuildConfig
import com.shipra.android.gitmobilesearch.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class GitInterceptor {

    private val TAG = "GitInterceptor"

    @Throws(IOException::class)
    fun intercept(chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val okhhtp3Url = originalRequest.url().newBuilder().build()
        requestBuilder.header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                .url(okhhtp3Url)
                .header(Constants.VERSION_CODE, BuildConfig.VERSION_CODE.toString() + "")
        return chain.proceed(requestBuilder.build())
    }

}