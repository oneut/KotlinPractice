package com.example.oneut.kotlinpractice.util.flux

interface StoreInterface {
    fun subscribe(callback: () -> Unit)
}

abstract class Store<T> : StoreInterface {
    private val reducer: Reducer<T>
    private var state: T
    private lateinit var callback: () -> Unit

    init {
        this.reducer = getReducer()
        this.state = this.reducer.getInitialState()
    }

    abstract fun getReducer() : Reducer<T>

    fun getState() : T {
        return this.state
    }

    fun dispatch(action: Action) {
        val newState = this.reduce(this.state, action)
        if (state !== newState) {
            this.state = newState
            this.callback()
        }
    }

    private fun reduce(state: T, action: Action) : T {
        return this.reducer.reduce(state, action)
    }

    override fun subscribe(callback: () -> Unit) {
        this.callback = callback
    }

    // @todo unsubscribe
}