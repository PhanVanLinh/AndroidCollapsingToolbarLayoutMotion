package com.example.androidcollapsingtoolbarlayoutmotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout


/**
 * Paralax the motionlayout
 * Animate the motion layout base on the
 */
class AppBarMotionParallaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar_motion_parallax)

        val toolbar = findViewById<Toolbar>(R.id.my_tool_bar)
        val appBarLayout = findViewById<AppBarLayout>(R.id.my_app_bar_layout)
        val motionLayout = findViewById<MotionLayout>(R.id.my_motion_layout)

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        // Desired collapsed height of toolbar
        val toolBarHeight = motionLayout.minimumHeight
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            // Resizing motion layout in a collapsed state with needed insets to not overlap    system bars
            val insetHeight = insets.systemWindowInsetTop
            motionLayout.minimumHeight = toolBarHeight + insetHeight

            // Update guidelines with givens insets
            val startConstraintSet = motionLayout.getConstraintSet(R.id.start)
            startConstraintSet.setGuidelineBegin(R.id.insets_guideline, insetHeight)

            val endConstraintSet = motionLayout.getConstraintSet(R.id.end)
            endConstraintSet.setGuidelineBegin(R.id.insets_guideline, insetHeight)
            endConstraintSet.setGuidelineEnd(R.id.collapsed_top_guideline, toolBarHeight)

            insets
        }
    }
}