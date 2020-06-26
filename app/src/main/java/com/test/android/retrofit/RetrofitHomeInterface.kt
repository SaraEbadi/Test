package com.test.android.retrofit

import com.test.android.pojo.ContentResponse
import com.test.android.pojo.DetailContent
import retrofit2.http.* // ktlint-disable no-wildcard-imports

/**
 * All retrofit interfaces.
 */
interface RetrofitHomeInterface {

    /**
     * Provides news data.
     */
    @GET("content/v1/contents")
    suspend fun getContents(@Query("contentTypeId") contentTypeId: Int):
            ContentResponse

    /**
     * Provides details of news.
     */
    @GET("content/v1/contents/{id}")
    suspend fun getDetailContent(@Path("id") id: Long): DetailContent
}
