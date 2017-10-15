package com.example.oneut.kotlinpractice.reducers

import com.example.oneut.kotlinpractice.actions.ItemsAction
import com.example.oneut.kotlinpractice.util.flux.Action
import com.example.oneut.kotlinpractice.util.flux.Reducer
import com.example.oneut.kotlinpractice.states.ItemsState
import java.util.*

class ItemsReducer : Reducer<ItemsState> {
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