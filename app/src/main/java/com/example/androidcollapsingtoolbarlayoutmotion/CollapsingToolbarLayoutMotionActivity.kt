package com.example.androidcollapsingtoolbarlayoutmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

class CollapsingToolbarLayoutMotionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar_layout_motion)

        val appBarLayout = findViewById<AppBarLayout>(R.id.my_app_bar_layout)
        val motionLayout = findViewById<MotionLayout>(R.id.my_motion_layout)

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }
    }

}