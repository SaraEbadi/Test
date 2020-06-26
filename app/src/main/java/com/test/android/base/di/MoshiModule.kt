package com.test.android.base.di

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

val moshiConverterFactoryModule = module {
    single {
        /**
         * Provides MoshiConverterFactory.
         */
        MoshiConverterFactory.create(get())
    }
}
val moshiModule = module {
    single {
        /**
         * Provides MoshiConverterFactory.
         */
        Moshi.Builder().build()
    }
}
