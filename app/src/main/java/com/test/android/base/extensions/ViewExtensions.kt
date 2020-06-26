package com.test.android.base.extensions

import android.view.View

/**
 * Makes a view visible.
 */
fun View.makeVisible() {
    visibility = View.VISIBLE
}

/**
 * Makes a view gone.
 */
fun View.makeGone() {
    visibility = View.GONE
}

