package com.example.oneut.kotlinpractice.actioncreators

import com.example.oneut.kotlinpractice.actions.ItemAction
import com.example.oneut.kotlinpractice.stores.ItemStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemActionCreator @Inject constructor(private val itemStore : ItemStore) {
    fun sync(item: HashMap<String, String>) {
        itemStore.dispatch(ItemAction.Sync(item))
    }
}
