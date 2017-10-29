package com.example.oneut.kotlinpractice

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
import com.example.oneut.kotlinpractice.util.flux.Container
import com.mikepenz.iconics.Iconics
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var subject: PublishSubject<Int>
    private lateinit var itemsAdapter: ListItemAdapter<_LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initialize()
        this.createView()
        this.createContainer()
        subject.onNext(1)
    }

    private fun initialize() {
        itemsAdapter = ItemsAdapter(this, ArrayList<Item>())
        subject = TopStoriesSubject().create(this, { item ->
            ItemsActionCreator.add(item)
        })
    }

    private fun createContainer() {
        val container = Container(arrayListOf(ItemsStore))
        container.subscribe {
            val state = ItemsStore.getState()
            val items = state.items.map { item ->
                Item(item)
            }

            itemsAdapter.clear()
            itemsAdapter.addAll(items)
            itemsAdapter.notifyDataSetChanged()
            toast("List Updated")
        }
    }

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
                    ItemsActionCreator.addAll(items)
                }
            }

            button {
                text = "Update list to 2 page"
                onClick {
                    subject.onNext(2)
                    subject.onComplete()
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