package com.test.android.base.di

import com.test.android.features.contents.contentlist.ContentListViewModel
import com.test.android.features.contents.detailcontent.DetailContentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailContentViewModel(get()) }
    viewModel { ContentListViewModel(get()) }
}
