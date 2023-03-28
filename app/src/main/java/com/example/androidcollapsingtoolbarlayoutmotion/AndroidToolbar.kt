package com.example.androidcollapsingtoolbarlayoutmotion

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

class AndroidToolbar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_toolbar)


        val appBarLayout = findViewById<AppBarLayout>(R.id.my_app_bar_layout)
        val motionLayout = findViewById<MotionLayout>(R.id.my_motion_layout)
        val toolbar = findViewById<View>(R.id.my_tool_bar)
        val toolbarBackground = findViewById<View>(R.id.my_tool_bar_background)
        val text_toolbar_title = findViewById<TextView>(R.id.text_toolbar_title)
        val toolbar_image = findViewById<ImageView>(R.id.toolbar_image)

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()

            toolbar_image.alpha = 1 - seekPosition

            toolbarBackground.alpha = seekPosition
            if (seekPosition > 0.5) {
                text_toolbar_title.setTextColor(Color.BLACK)
            } else {
                text_toolbar_title.setTextColor(Color.WHITE)
            }
        }
    }
}