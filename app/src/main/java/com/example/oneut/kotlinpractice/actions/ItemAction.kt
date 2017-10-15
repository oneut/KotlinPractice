package com.example.oneut.kotlinpractice.actions

import com.example.oneut.kotlinpractice.util.flux.Action

class ItemAction {
    class Sync(val item: HashMap<String, String>) : Action
}