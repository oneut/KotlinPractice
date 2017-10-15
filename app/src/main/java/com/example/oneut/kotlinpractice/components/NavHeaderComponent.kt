package com.example.oneut.kotlinpractice.components

import android.os.Build
import android.support.design.widget.NavigationView
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.oneut.kotlinpractice.R
import com.example.oneut.kotlinpractice.R.style.TextAppearance_AppCompat_Body1
import com.example.oneut.kotlinpractice.R.style.ThemeOverlay_AppCompat_Dark
import org.jetbrains.anko.*

class NavHeaderComponent : AnkoComponent<NavigationView> {
    override fun createView(ui: AnkoContext<NavigationView>): View = with(ui) {
        themedLinearLayout(ThemeOverlay_AppCompat_Dark) {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.BOTTOM

            verticalPadding = dimen(R.dimen.activity_vertical_margin)
            horizontalPadding = dimen(R.dimen.activity_horizontal_margin)

            backgroundResource = R.drawable.side_nav_bar
            imageView(android.R.drawable.sym_def_app_icon) {
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
            }.lparams {
                gravity = Gravity.START
            }

            textView("Android Studio") {
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(TextAppearance_AppCompat_Body1)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, TextAppearance_AppCompat_Body1)
                }
            }

            textView("android.studio@android.com")
        }
    }
}