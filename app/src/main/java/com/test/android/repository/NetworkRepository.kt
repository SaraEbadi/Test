package com.test.android.repository

import com.test.android.retrofit.*

/**
 * All network observables are stored here.
 */
class NetworkRepository(
    private val retrofitHomeInterface: RetrofitHomeInterface
) {

    /**
     * Returns contents including news and articles.
     */
    suspend fun getContents(contents: Int) = retrofitHomeInterface.getContents(contents)

    /**
     * Get detailContent response when clicked item of contentList.
     */
    suspend fun getDetailContent(id: Long) = retrofitHomeInterface.getDetailContent(id)

}
