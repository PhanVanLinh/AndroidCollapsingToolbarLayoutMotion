package com.example.androidcollapsingtoolbarlayoutmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_tool_bar))
    }

    // 1. CoordinatorLayout
    // AppBarLayout
    //  Collapsing toolbar
        // scroll flag
    // Layout bebehaviour: scroll
}