package com.test.android.base.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.android.R

/**
 * Create decoration for items in recyclerview.
 **/
fun RecyclerView.setItemDecoration(context: Context, orientation: Int) {
    val decoration = DividerItemDecoration(context, orientation)
    decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.inset_item_decorator)!!)
    addItemDecoration(decoration)
}

/**
 * on scroll listener for linearLayouts.
 */
fun RecyclerView.setAddOnScrollListener(callNextPage: () -> Unit) {
    val layoutManager = layoutManager!!
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val total = layoutManager.itemCount
            if(layoutManager is LinearLayoutManager){
                val lastItem = layoutManager.findLastVisibleItemPosition()
                if (total > 0 && total - 1 == lastItem)
                    callNextPage()
            }
        }
    })
}