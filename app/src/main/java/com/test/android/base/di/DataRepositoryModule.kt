package com.test.android.base.di

import com.test.android.repository.NetworkRepository
import org.koin.dsl.module

val dataRepositoryModule = module {

    single {
        NetworkRepository(get())
    }
}
