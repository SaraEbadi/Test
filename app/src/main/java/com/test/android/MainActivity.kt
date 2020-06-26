package com.test.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * used single activity approach.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
