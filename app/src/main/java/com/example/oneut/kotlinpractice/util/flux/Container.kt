package com.example.oneut.kotlinpractice.util.flux

class Container(private val stores: ArrayList<StoreInterface>) {
    fun subscribe(subscriber: Subscriber) {
        stores.forEach { store -> store.subscribe(subscriber) }

        subscriber()
    }

    fun unsubscribe(subscriber: Subscriber) {
        stores.forEach { store -> store.unsubscribe(subscriber) }
    }
}