package com.test.android.base.di

import com.test.android.BuildConfig
import com.test.android.retrofit.RetrofitHomeInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single {
        /**
         * Provides and configs logger to see the logs in terminal.
         */
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
    single {
        /**
         * Provides base url.
         */
        BuildConfig.BASE_URL
    }

    single {
        /**
         * Provides and configs okHttp to have authorization header.
         */
        val httpClient = OkHttpClient().newBuilder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.callTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(get<HttpLoggingInterceptor>())
        httpClient
    }

    single {
        /**
         * Provides the retrofit with authorization object.
         */
        Retrofit.Builder()
            .baseUrl(get<String>())
            .addConverterFactory(get<MoshiConverterFactory>())
            .client(get<OkHttpClient.Builder>().build())
            .build()
    }

    single {
        /**
         * Provides the retrofit home features interface.
         */
        get<Retrofit>().create(RetrofitHomeInterface::class.java)
    }

}
