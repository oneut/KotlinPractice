package com.example.oneut.kotlinpractice.util.flux

class Container(private val stores: ArrayList<StoreInterface>) {
    fun subscribe(callback: () -> Unit) {
        stores.forEach { store ->
            store.subscribe {
                callback()
            }
        }

        callback()
    }
}