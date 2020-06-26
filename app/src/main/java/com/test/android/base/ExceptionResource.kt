package com.test.android.base

sealed class ExceptionResource(message: String) {
    class IOException(message: String) : ExceptionResource(message)
    class UnAuthorizedException(message: String) : ExceptionResource(message)
}
