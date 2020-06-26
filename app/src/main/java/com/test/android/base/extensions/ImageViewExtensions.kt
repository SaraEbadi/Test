package com.test.android.base.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Loads images into ImageViews.
 */
fun ImageView.loadUrl(url: String, placeholder: Int? = null) {
    val picasso = Picasso.get().load(url)
    placeholder?.let { picasso.placeholder(it) }
    picasso.into(this)
}
