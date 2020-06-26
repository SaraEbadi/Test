package com.test.android.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base viewModel to provide needed features in all Child class viewModels.
 */
open class BaseViewModel : ViewModel() {
    protected val exceptionLiveData = MutableLiveData<ExceptionResource>()
}
