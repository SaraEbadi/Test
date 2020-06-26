package com.test.android.features.contents.contentlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.android.pojo.DetailContent

/**
 * DiffUtil Class for [ContentListAdapter] that checks [DetailContent.id] && [DetailContent.title]
 * to avoid duplicate items in a list of type [DetailContent].
 */
class ContentListDiffUtilsCallback : DiffUtil.ItemCallback<DetailContent>() {
    override fun areItemsTheSame(oldItem: DetailContent, newItem: DetailContent) =
        oldItem.id == newItem.id && oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: DetailContent, newItem: DetailContent) =
        oldItem.id == newItem.id && oldItem.title == newItem.title
}
