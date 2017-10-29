package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemsAction
import com.example.oneut.kotlinpractice.stores.ItemsStore

object ItemsActionCreator {
    fun addAll(items: ArrayList<HashMap<String, String>>) {
        ItemsStore.dispatch(ItemsAction.AddAll(items))
    }

    fun add(item: HashMap<String, String>) {
        ItemsStore.dispatch(ItemsAction.Add(item))
    }
}
