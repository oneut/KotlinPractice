package com.example.oneut.kotlinpractice

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    fun inject(app: MainActivity)
    fun inject(app: ListItemActivity)
}