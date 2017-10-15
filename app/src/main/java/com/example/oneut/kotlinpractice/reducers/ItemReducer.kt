package com.example.oneut.kotlinpractice.reducers

import com.example.oneut.kotlinpractice.actions.ItemAction
import com.example.oneut.kotlinpractice.util.flux.Action
import com.example.oneut.kotlinpractice.util.flux.Reducer
import com.example.oneut.kotlinpractice.states.ItemState

class ItemReducer : Reducer<ItemState> {
    override fun getInitialState() : ItemState {
        return ItemState(hashMapOf())
    }

    override fun reduce(state: ItemState, action: Action): ItemState {
        return when (action) {
            is ItemAction.Sync -> {
                ItemState(action.item)
            }
            else -> state
        }
    }
}