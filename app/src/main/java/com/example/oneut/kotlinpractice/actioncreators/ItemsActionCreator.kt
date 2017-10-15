package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemsAction
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.states.ItemsState

class ItemsActionCreator(private val store: Store<ItemsState>) {
    fun addAll(items: ArrayList<HashMap<String, String>>) {
        store.dispatch(ItemsAction.AddAll(items))
    }

    fun add(item: HashMap<String, String>) {
        store.dispatch(ItemsAction.Add(item))
    }
}
