package com.test.android.features.contents.contentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.android.base.BaseViewModel
import com.test.android.features.contents.ContentsResource
import com.test.android.pojo.DetailContent
import com.test.android.repository.NetworkRepository
import com.test.android.utils.CONTENT_TYPE
import com.test.android.utils.coroutinesExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel class for Content service.
 *
 * @property repository The repository for call API service.
 */
class ContentListViewModel(private val repository: NetworkRepository) : BaseViewModel() {
    private val contentListData = MutableLiveData<ContentsResource<List<DetailContent>>>()
    private var data = mutableListOf<DetailContent>()

    /**
     * Represents API calling and fill mutableLiveData with received data for pass to UI layer.
     *
     * @param contentTypeId The id of type content that included news or articles.
     */
    fun getDataContentList(contentTypeId: Int) {
        contentListData.value = ContentsResource.Loading()
        viewModelScope.launch(coroutinesExceptionHandler(exceptionLiveData)) {
            val response = repository.getContents(contentTypeId)
            data.addAll(response.elements)
            contentListData.value = ContentsResource.Success(data)
        }
    }

    /**
     * Converts [contentListData] mutableLiveData to LiveData with [DetailContent] model.
     *
     * @return The list of [DetailContent] model which is a liveData and is wrapped by [ContentsResource].
     */
    fun contentListResponseLiveData(): LiveData<ContentsResource<List<DetailContent>>> =
        contentListData

}
