package com.example.androidcollapsingtoolbarlayoutmotion

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateMarginsRelative
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout

class CollapsingToolbarMotionParallaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_collapsing_toolbar_motion_parallax)

        val appBarLayout = findViewById<AppBarLayout>(R.id.my_app_bar_layout)
        val motionLayout = findViewById<ConstraintLayout>(R.id.my_motion_layout)
        val collapseToolbar = findViewById<CollapsingToolbarLayout>(R.id.my_collapse_toolbar)
        val imageSerach = findViewById<ImageView>(R.id.image_search)
        val topToolbarImage = findViewById<View>(R.id.image_navigation)
        val topToolbarBg = findViewById<View>(R.id.bg_top_toolbar)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val editText = findViewById<EditText>(R.id.my_edit_text)
        val my_nested_scrollview = findViewById<NestedScrollView>(R.id.my_nested_scrollview)
        my_nested_scrollview.isNestedScrollingEnabled = true

        ViewCompat.setOnApplyWindowInsetsListener(appBarLayout) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val appBarTop = insets.top + appBarLayout.paddingTop
            collapseToolbar.minimumHeight = appBarTop
            (motionLayout.layoutParams as MarginLayoutParams).updateMarginsRelative(top = appBarTop)
            WindowInsetsCompat.CONSUMED
        }

        tabLayout.addTab(tabLayout.newTab().setText("ABC"))
        tabLayout.addTab(tabLayout.newTab().setText("DEF"))

        val dp16 = resources.getDimensionPixelSize(R.dimen.dp_16)
        val dp48 = resources.getDimensionPixelSize(R.dimen.dp_48)
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()

            motionLayout.alpha = Math.pow((1 - seekPosition).toDouble(), 2.0 * 4).toFloat()
            topToolbarImage.alpha = Math.pow((1 - seekPosition).toDouble(), 2.0).toFloat()
            topToolbarBg.alpha = 0f

            val layoutParams = editText.layoutParams as MarginLayoutParams
            val oldMargin = layoutParams.marginStart
            val newMargin = (seekPosition * dp48).toInt() + dp16
            if (oldMargin != newMargin) {
                layoutParams.updateMarginsRelative(newMargin)
                editText.layoutParams = layoutParams
            }

            imageSerach.alpha = Math.pow(seekPosition.toDouble(), 3.0).toFloat()
            tabLayout.getChildAt(0).alpha = Math.pow((1 - seekPosition).toDouble(), 2.0 * 16).toFloat()

            WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = seekPosition == 1f

            updateStatusBarColor(seekPosition)
        }
    }

    private fun updateStatusBarColor(seekPosition: Float = 0f) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (seekPosition == 1f) {
                WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true
                return
            }
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
        } else {
            window.statusBarColor = Color.argb(150, 0, 0, 0)
        }
    }
}