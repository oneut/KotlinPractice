package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemAction
import com.example.oneut.kotlinpractice.util.flux.Store
import com.example.oneut.kotlinpractice.states.ItemState

class ItemActionCreator(private val store: Store<ItemState>) {
    fun sync(item: HashMap<String, String>) {
        store.dispatch(ItemAction.Sync(item))
    }
}
