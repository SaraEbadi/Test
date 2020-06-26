package com.test.android.base

import android.app.Application
import com.test.android.base.di.* // ktlint-disable no-wildcard-imports
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * BaseApplication class to provide app level dependencies
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                moshiConverterFactoryModule,
                moshiModule,
                dataRepositoryModule,
                retrofitModule,
                viewModelModule
            )
        }
    }
}
