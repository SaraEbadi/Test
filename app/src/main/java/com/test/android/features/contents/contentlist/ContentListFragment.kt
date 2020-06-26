package com.test.android.features.contents.contentlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.android.R
import com.test.android.base.extensions.makeGone
import com.test.android.base.extensions.makeVisible
import com.test.android.base.extensions.setItemDecoration
import com.test.android.features.contents.ContentsResource
import com.test.android.features.contents.contentlist.adapter.ContentListAdapter
import com.test.android.pojo.DetailContent
import kotlinx.android.synthetic.main.content_list_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Contents Fragment that included news item.
 */
open class ContentListFragment : Fragment(R.layout.content_list_fragment) {
    private lateinit var contentListAdapter: ContentListAdapter
    private val viewModel: ContentListViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareContentList()
        setUpRecyclerView()
    }

    private fun prepareContentList() {
        viewModel.contentListResponseLiveData().removeObservers(viewLifecycleOwner)
        viewModel.contentListResponseLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ContentsResource.Success -> hideLoaderEmpty(it.data.orEmpty())
                is ContentsResource.Error -> hideLoader()
                is ContentsResource.Loading -> prgContentList.makeVisible()
            }
        })
        viewModel.getDataContentList(1)
    }

    private fun hideLoaderEmpty(list: List<DetailContent>) {
        val dataList: MutableList<DetailContent> = mutableListOf()
        for (detailContent in list)
            dataList.add(detailContent)
        if (dataList.isNullOrEmpty())
            hideLoader()
        else {
            contentListAdapter.submitList(dataList)
            prgContentList.makeGone()
            recContentList.makeVisible()
        }
    }

    private fun hideLoader() {
        prgContentList.makeGone()
        recContentList.makeGone()
    }

    private fun setUpRecyclerView() {
        contentListAdapter = ContentListAdapter { transferToDetailContent(it) }
        recContentList.adapter = contentListAdapter
        recContentList.setItemDecoration(
            requireContext(), (recContentList.layoutManager as LinearLayoutManager).orientation
        )
    }

    private fun transferToDetailContent(id: Long) {
        val action =
            ContentListFragmentDirections.actionContentListFragmentToDetailContentFragment(id)
        findNavController().navigate(action)
    }
}
