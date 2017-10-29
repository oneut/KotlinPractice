package com.example.oneut.kotlinpractice.stores

import com.example.oneut.kotlinpractice.util.flux.Reducer
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.reducers.ItemReducer
import com.example.oneut.kotlinpractice.states.ItemState

object ItemStore : Store<ItemState>() {
    override fun getReducer() : Reducer<ItemState> {
        return ItemReducer()
    }
}