package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemsAction
import com.example.oneut.kotlinpractice.stores.ItemsStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsActionCreator @Inject constructor(private val itemsStore : ItemsStore) {
    fun addAll(items: ArrayList<HashMap<String, String>>) {
        itemsStore.dispatch(ItemsAction.AddAll(items))
    }

    fun add(item: HashMap<String, String>) {
        itemsStore.dispatch(ItemsAction.Add(item))
    }
}
