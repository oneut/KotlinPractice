package com.example.oneut.kotlinpractice

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import com.example.oneut.kotlinpractice.actioncreators.ItemsActionCreator
import com.example.oneut.kotlinpractice.api.hackernews.subjects.TopStoriesSubject
import com.example.oneut.kotlinpractice.stores.ItemsStore
import com.example.oneut.kotlinpractice.util.arrayadapter.ListItem
import com.example.oneut.kotlinpractice.util.arrayadapter.ListItemAdapter
import com.example.oneut.kotlinpractice.util.flux.Subscriber
import com.example.oneut.kotlinpractice.util.flux.Container
import com.mikepenz.iconics.Iconics
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var subject: PublishSubject<Int>
    private lateinit var itemsAdapter: ListItemAdapter<_LinearLayout>

    @Inject lateinit var itemsStore : ItemsStore
    @Inject lateinit var itemsActionCreator : ItemsActionCreator
    @Inject lateinit var topStoriesSubject: TopStoriesSubject

    private val subscriber : Subscriber = {
        val state = itemsStore.getState()
        val items = state.items.map { item ->
            Item(item)
        }

        itemsAdapter.clear()
        itemsAdapter.addAll(items)
        itemsAdapter.notifyDataSetChanged()
    }

    private lateinit var container: Container
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)
        this.initialize()
        this.createView()
        this.createContainer()
    }

    private fun initialize() {
        itemsAdapter = ItemsAdapter(this, ArrayList<Item>())
        subject = topStoriesSubject.create(this, { item ->
            itemsActionCreator.add(item)
        })
        subject.onNext(page++)
    }

    private fun createContainer() {
        container = Container(arrayListOf(itemsStore))
        container.subscribe(subscriber)
    }

    @SuppressLint("SetTextI18n")
    private fun createView() {
        verticalLayout {
            textView {
                text = Iconics
                        .IconicsBuilder()
                        .ctx(this.context)
                        .style(ForegroundColorSpan(Color.BLUE))
                        .on("Kotlin Practice {faw-android}")
                        .build()
                textSize = 36f
            }

            button {
                text = "Say Hello"
                onClick {
                    toast("Hello")
                }
            }

            button {
                text = "Move Drawer"
                onClick {
                    startActivity<DrawerActivity>()
                }
            }

            button {
                text = "Add 'hello' to list"
                onClick {
                    val items = ArrayList<HashMap<String, String>>()
                    items.add(hashMapOf("title" to "hello"))

                    itemsActionCreator.addAll(items)
                }
            }

            button {
                text = "Update list to " + page.toString() + " page"
                onClick {
                    subject.onNext(page++)
                    text = "Update list to " + page.toString() + " page"
                    toast("List Updated")
                }
            }

            listView {
                adapter = itemsAdapter
            }
        }
    }
}

internal class ItemsAdapter(ctx: Context, items: List<ListItem<_LinearLayout>>) : ListItemAdapter<_LinearLayout>(ctx, items) {
    override val listItemClasses = arrayListOf<Class<out ListItem<_LinearLayout>>>(Item::class.java)
}

internal class Item(val item: HashMap<String, String>) : ListItem<_LinearLayout> {
    private lateinit var textView: TextView
    override fun createTextView(ui: AnkoContext<ListItemAdapter<_LinearLayout>>, init: _LinearLayout.() -> Unit) = ui.apply {
        verticalLayout {
            textView = textView {
                id = android.R.id.text1
                text = item["title"].toString()
                init()
            }
            onClick {
                toast("Say")
                startActivity<ListItemActivity>("id" to item["id"].toString())
            }
            isClickable = true
        }
    }.view

    override fun apply(convertView: View) {
        val h = getHolder(convertView)
        textView = h.view.find(android.R.id.text1)
        textView.text = item.get("title").toString()
        h.view.onClick {
            convertView.context.startActivity<ListItemActivity>("id" to item["id"].toString())
        }
    }
}