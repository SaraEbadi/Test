package com.test.android.features.contents.contentlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.android.R
import com.test.android.base.extensions.loadUrl
import com.test.android.base.extensions.timeAgo
import com.test.android.base.extensions.toPersianNumber
import com.test.android.pojo.DetailContent
import com.test.android.utils.BASE_CONTENT_IMAGE_URL
import kotlinx.android.synthetic.main.item_content_list.view.*

/**
 * An adapter for contentList page to generate and prepare a list of data with [DetailContent] model.
 *
 * @property clickListener reaction to click on each item in the list content.
 */
class ContentListAdapter(private val clickListener: (Long) -> Unit) :
    ListAdapter<DetailContent,
            ContentListAdapter.ContentListViewHolder>(ContentListDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_content_list, parent, false)
        return ContentListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentListViewHolder, position: Int) =
        holder.bind(getItem(position))

    /**
     * A viewHolder class for [ContentListAdapter].
     *
     * @constructor
     * Creates an instance of this class with the given [itemView].
     *
     * @param itemView The view for hold each item of data in each item view.
     */
    inner class ContentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds contents data to view items.
         *
         * @param detailContent the item of data in response model [DetailContent].
         */
        fun bind(detailContent: DetailContent) {
            itemView.txtTitleContent.text = detailContent.title.toPersianNumber()
            itemView.txtContentTime.text = detailContent.publishDate.timeAgo()?.toPersianNumber()
            itemView.imgContentList.loadUrl(BASE_CONTENT_IMAGE_URL + detailContent.thumbnailPath)
            itemView.setOnClickListener { clickListener(detailContent.id) }
        }
    }
}
