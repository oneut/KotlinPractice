package com.example.oneut.kotlinpractice.util.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.oneut.kotlinpractice.R
import com.example.oneut.kotlinpractice.components.NavHeaderComponent
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.drawerLayout

abstract class DrawerUi<T> : AnkoComponent<T> {
    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar

    abstract fun <T> AnkoContext<T>.content(): View?

    override fun createView(ui: AnkoContext<T>): View = with(ui) {
        drawer = drawerLayout {
            coordinatorLayout {
                themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                    toolbar = toolbar {
                        popupTheme = R.style.AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = matchParent)
                }.lparams(width = matchParent)

                with(AnkoContext.createDelegate(relativeLayout {
                    val relativeLayoutParams = CoordinatorLayout.LayoutParams(matchParent, matchParent)
                    relativeLayoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
                    layoutParams = relativeLayoutParams
                })) {
                    content()
                }

                floatingActionButton {
                    imageResource = android.R.drawable.ic_dialog_email
                }.lparams {
                    margin = dimen(R.dimen.fab_margin)
                    gravity = Gravity.BOTTOM or GravityCompat.END
                }
            }.lparams(width = matchParent, height = matchParent)

            navigationView {
                fitsSystemWindows = true
                val headerContext = AnkoContext.create(ctx, this)
                val headerView = NavHeaderComponent()
                        .createView(headerContext)
                addHeaderView(headerView)

                inflateMenu(R.menu.menu)
            }.lparams(height = matchParent) {
                gravity = GravityCompat.START
            }

            if (isInEditMode) {
                openDrawer(GravityCompat.START)
            }
        }

        drawer
    }
}