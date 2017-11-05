package com.example.oneut.kotlinpractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.oneut.kotlinpractice.actioncreators.ItemActionCreator
import com.example.oneut.kotlinpractice.api.hackernews.subjects.ItemSubject
import com.example.oneut.kotlinpractice.stores.ItemStore
import com.example.oneut.kotlinpractice.util.flux.Container
import com.example.oneut.kotlinpractice.util.flux.Subscriber
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.*
import javax.inject.Inject

class ListItemActivity : AppCompatActivity() {

    private lateinit var subject: PublishSubject<Long>
    private lateinit var textView: TextView

    @Inject lateinit var itemStore : ItemStore
    @Inject lateinit var itemActionCreator : ItemActionCreator
    @Inject lateinit var itemSubject: ItemSubject

    private val subscriber : Subscriber = {
        val state = itemStore.getState()
        textView.text = "title: " + state.item["title"].toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        this.initialize()
        this.createView()
        this.createContainer()
    }

    private fun initialize() {
        subject = itemSubject.create(this, { item: HashMap<String, String> ->
            itemActionCreator.sync(item)
        })
        subject.onNext(intent.getStringExtra("id").toLong())
    }

    private fun createContainer() {
        val container = Container(arrayListOf(itemStore))
        container.subscribe(subscriber)
    }

    private fun createView() {
        verticalLayout {
            textView = textView("title: loading")
        }
    }
}
