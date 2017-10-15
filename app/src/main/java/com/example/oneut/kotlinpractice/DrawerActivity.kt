package com.example.oneut.kotlinpractice

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.oneut.kotlinpractice.util.ui.DrawerUi
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoContext

class DrawerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = MyActivityUI()
        ui.setContentView(this)
        val drawer = ui.drawer
        setSupportActionBar(ui.toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer, ui.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }
}

class MyActivityUI : DrawerUi<DrawerActivity>() {
    override fun <T> AnkoContext<T>.content(): View {
        return verticalLayout {
            lparams(width = matchParent, height = matchParent)
            textView("Hello, Drawer")
        }
    }
}