package com.test.android.features.contents

import com.test.android.features.contents.ContentsResource.* // ktlint-disable no-wildcard-imports
import com.test.android.pojo.DetailContent

/**
 * When a service gets called, the response is wrapped by this model.
 * The response status can be [Success] or [Loading].
 * Error status is handled by [Error].
 *
 * @param T the type of response model, e.g:[DetailContent] and it's generic.
 * @property data the value of response with [T] type.
 * @property message the message of Error response.
 */
sealed class ContentsResource<T>(val data: T? = null, val message: String? = null) {

    /**
     * Used to wrapping the successful response of Content.
     *
     * @param T the type of response model e.g:[DetailContent] and its generic.
     * @constructor
     * Creates an instance of this class with the given [data].
     *
     * @property data the value of response with [T] type.
     */
    class Success<T>(data: T) : ContentsResource<T>(data)

    /**
     * Used to wrapping the loading response of Content.
     *
     * @param T the type of response model e.g:[DetailContent] and its generic, it can be nullable.
     * @constructor
     * Creates an instance of this class with the given [data].
     *
     * @property data the value of response with [T] type.
     */
    class Loading<T>(data: T? = null) : ContentsResource<T>(data)

    /**
     * Used to wrapping the error response of Content.
     *
     * @property message the message of Error response.
     * @param T the type of response model e.g:[DetailContent] and its generic, it can be nullable.
     * @property data the value of response with [T] type.
     */
    class Error<T>(massage: String, data: T? = null) : ContentsResource<T>(data, massage)
}
