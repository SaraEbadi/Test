package com.test.android.utils

import androidx.lifecycle.MutableLiveData
import com.test.android.base.ExceptionResource
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.UnknownHostException

/**
 * Used to Exception handler of coroutines for cases different status code of error.
 *
 * @param liveData The mutableLiveData of error response.
 */
fun coroutinesExceptionHandler(liveData: MutableLiveData<ExceptionResource>) =
    CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is InterruptedIOException, is UnknownHostException ->
                liveData.value = ExceptionResource.IOException(throwable.message.orEmpty())
            is HttpException -> handleHttpException(liveData, throwable)
            else -> throw IllegalStateException(
                "Error is not handled properly ${throwable.localizedMessage}"
            )
        }
    }

private fun handleHttpException(
    liveData: MutableLiveData<ExceptionResource>, throwable: HttpException
) {
    liveData.value = ExceptionResource.IOException(throwable.message.orEmpty())
}
