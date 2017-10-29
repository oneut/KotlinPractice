package com.example.oneut.kotlinpractice.stores

import com.example.oneut.kotlinpractice.util.flux.Reducer
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.reducers.ItemsReducer
import com.example.oneut.kotlinpractice.states.ItemsState

object ItemsStore : Store<ItemsState>() {
    override fun getReducer() : Reducer<ItemsState> {
        return ItemsReducer()
    }
}