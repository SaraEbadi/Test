package com.test.android.features.contents.detailcontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.test.android.R
import com.test.android.base.extensions.*
import com.test.android.features.contents.ContentsResource
import com.test.android.pojo.DetailContent
import com.test.android.utils.BASE_CONTENT_IMAGE_URL
import com.test.android.utils.CONTENT_ID
import kotlinx.android.synthetic.main.detail_content_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * DetailContent Fragment. access to this page is from the ContentList page or click on deepLink.
 * User can navigate to this fragment by performing click on each list items.
 * It receives a parameter: [contentId] which are required to move to this page.
 *
 * @property contentTypeId The id that received from SharedPreferences.
 * @property contentId safe id will be sent from ContentList page for display information.
 */
class DetailContentFragment : Fragment(R.layout.detail_content_fragment) {
    private var contentTypeId: Int? = null
    private var contentId: Long = 0
    private val viewModel: DetailContentViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        prepareDetailContent()
    }

    private fun initToolbar() {
        txtTitle.text = getString(R.string.news_text)
        imgBack.setImageResource(R.drawable.ic_back_white)
    }

    private fun init() {
        contentId = arguments?.getLong(CONTENT_ID) ?: -1
        require(contentId != -1L) { R.string.id_not_exist }
        imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun prepareDetailContent() {
        viewModel.detailContentLiveData().removeObservers(viewLifecycleOwner)
        viewModel.detailContentLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ContentsResource.Success -> if (it.data != null) fillView(it.data)
                is ContentsResource.Error -> prgDetailContent.makeGone()
                is ContentsResource.Loading -> prgDetailContent.makeVisible()
            }
        })
        viewModel.getContentDetail(contentId)
    }

    private fun fillView(it: DetailContent) {
        imgContent.loadUrl(BASE_CONTENT_IMAGE_URL + it.imagePath)
        txtContentTime.text = it.publishDate.timeAgo()?.toPersianNumber()
        txtTitleContent.text = it.title.toPersianNumber()
        txtContent.text = it.contentBody?.toPersianNumber()
        contentTypeId = it.contentType?.id
        prgDetailContent.makeGone()
        initToolbar()
    }

    private fun onBackPressed() {
        findNavController().navigateUp()
    }
}
