package com.example.oneut.kotlinpractice.stores

import com.example.oneut.kotlinpractice.actions.ItemAction
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.states.ItemState
import com.example.oneut.kotlinpractice.util.flux.Action
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemStore @Inject constructor() : Store<ItemState>() {
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