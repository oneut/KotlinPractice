package com.example.oneut.kotlinpractice.actions

import com.example.oneut.kotlinpractice.util.flux.Action

class ItemsAction {
    class AddAll(val items: ArrayList<HashMap<String, String>>) : Action
    class Add(val item: HashMap<String, String>) : Action
}