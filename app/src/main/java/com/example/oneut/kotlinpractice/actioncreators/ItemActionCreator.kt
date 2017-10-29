package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemAction
import com.example.oneut.kotlinpractice.stores.ItemStore

object ItemActionCreator {
    fun sync(item: HashMap<String, String>) {
        ItemStore.dispatch(ItemAction.Sync(item))
    }
}
