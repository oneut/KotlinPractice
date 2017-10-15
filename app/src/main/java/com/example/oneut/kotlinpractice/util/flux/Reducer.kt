package com.example.oneut.kotlinpractice.util.flux

interface Reducer<T> {
    fun getInitialState(): T
    fun reduce(state: T, action: Action): T
}