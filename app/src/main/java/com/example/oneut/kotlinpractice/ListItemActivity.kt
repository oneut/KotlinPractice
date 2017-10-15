package com.example.oneut.kotlinpractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.oneut.kotlinpractice.actioncreators.ItemActionCreator
import com.example.oneut.kotlinpractice.api.hackernews.subjects.ItemSubject
import com.example.oneut.kotlinpractice.states.ItemState
import com.example.oneut.kotlinpractice.stores.ItemStore
import com.example.oneut.kotlinpractice.util.flux.Store
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.*

class ListItemActivity : AppCompatActivity() {

    private lateinit var itemActionCreator: ItemActionCreator
    private lateinit var store: Store<ItemState>
    private lateinit var subject: PublishSubject<Long>
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initialize()
        this.createView()
    }

    private fun initialize() {
        store = ItemStore()
        store.subscribe { state ->
            textView.text = "title: " + state.item["title"].toString()
            toast(state.item["title"].toString())
        }

        itemActionCreator = ItemActionCreator(store)
        subject = ItemSubject().create(this, { item: HashMap<String, String> ->
            itemActionCreator.sync(item)
        })
        subject.onNext(intent.getStringExtra("id").toLong())
    }

    private fun createView() {
        verticalLayout {
            textView = textView("title: loading")
        }
    }
}
