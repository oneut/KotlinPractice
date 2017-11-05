package com.example.oneut.kotlinpractice.util.flux

typealias Subscriber = () -> Unit

interface StoreInterface {
    fun subscribe(callback: Subscriber)
    fun unsubscribe(callback: Subscriber)
}

abstract class Store<T> : StoreInterface {
    private var state: T
    private val subscribers: ArrayList<Subscriber> = arrayListOf()

    init {
        this.state = this.getInitialState()
    }

    abstract fun getInitialState() : T
    abstract fun reduce(state: T, action: Action): T

    fun getState(): T {
        return this.state
    }

    fun dispatch(action: Action) {
        val newState = this.reduce(this.state, action)
        if (state !== newState) {
            this.state = newState
            this.subscribers.forEach { subscriber -> subscriber() }
        }
    }

    override fun subscribe(subscriber: Subscriber) {
        this.subscribers += subscriber
    }

    override fun unsubscribe(subscriber: Subscriber) {
        this.subscribers -= subscriber
    }
}