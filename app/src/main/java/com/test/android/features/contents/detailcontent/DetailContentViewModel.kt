package com.test.android.features.contents.detailcontent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.android.base.BaseViewModel
import com.test.android.features.contents.ContentsResource
import com.test.android.pojo.DetailContent
import com.test.android.repository.NetworkRepository
import com.test.android.utils.coroutinesExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel class for Content service, this page has detail of content.
 *
 * @property repository The repository for call API service.
 */
class DetailContentViewModel(private val repository: NetworkRepository) : BaseViewModel() {
    private val detailContentData = MutableLiveData<ContentsResource<DetailContent>>()

    /**
     * Converts [detailContentData] mutableLiveData to LiveData with [DetailContent] model.
     *
     * @return the list of [DetailContent] model which is a liveData and is wrapped by [ContentsResource].
     */
    fun detailContentLiveData(): LiveData<ContentsResource<DetailContent>> = detailContentData

    /**
     * Calling Content API from [NetworkRepository.getDetailContent].
     *
     * @param id The id that can be entered by clicking on a list item.
     */
    fun getContentDetail(id: Long) {
        detailContentData.value = ContentsResource.Loading()
        viewModelScope.launch(coroutinesExceptionHandler(exceptionLiveData)) {
            val response = repository.getDetailContent(id)
            detailContentData.value = ContentsResource.Success(response)
        }
    }
}
