package com.example.oneut.kotlinpractice.util.flux

abstract class Store<T> {
    private val reducer: Reducer<T>
    private var state: T
    private lateinit var callback: (T) -> Unit

    init {
        this.reducer = getReducer()
        this.state = this.reducer.getInitialState()
    }

    abstract fun getReducer() : Reducer<T>

    fun dispatch(action: Action) {
        val newState = this.reduce(this.state, action)
        if (state !== newState) {
            this.state = newState
            this.callback(this.state)
        }
    }

    private fun reduce(state: T, action: Action) : T {
        return this.reducer.reduce(state, action)
    }

    fun subscribe(callback: (T) -> Unit) {
        this.callback = callback
    }
}