package com.example.oneut.kotlinpractice.stores

import com.example.oneut.kotlinpractice.actions.ItemsAction
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.states.ItemsState
import com.example.oneut.kotlinpractice.util.flux.Action
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsStore @Inject constructor() : Store<ItemsState>() {
    override fun getInitialState() : ItemsState {
        return ItemsState(arrayListOf())
    }

    override fun reduce(state: ItemsState, action: Action): ItemsState {
        return when (action) {
            is ItemsAction.AddAll -> {
                val newItems = state.items.toMutableList()
                newItems.addAll(action.items)
                ItemsState(ArrayList(newItems))
            }
            is ItemsAction.Add -> {
                val newItems = state.items.toMutableList()
                newItems.add(action.item)
                ItemsState(ArrayList(newItems))
            }
            else -> state
        }
    }
}