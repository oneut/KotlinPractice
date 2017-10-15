package com.example.oneut.kotlinpractice.util.arrayadapter

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding

abstract class ListItemAdapter<T>(ctx: Context, items: List<ListItem<T>>) : ArrayAdapter<ListItem<T>>(ctx, 0, items) {
    private val ankoContext = AnkoContext.createReusable(ctx, this)

    protected abstract val listItemClasses: List<Class<out ListItem<T>>>

    private val types: Map<Class<out ListItem<T>>, Int> by lazy {
        listItemClasses.withIndex().fold(hashMapOf<Class<out ListItem<T>>, Int>()) { map, t ->
            map.put(t.value, t.index); map
        }
    }

    override fun getViewTypeCount(): Int = types.size
    
    override fun getItemViewType(position: Int) = types[getItem(position)?.javaClass as Class<out ListItem<T>>] ?: 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = getItem(position)
        return if (item != null) {
            val view = convertView ?: item.createView(ankoContext)
            item.apply(view)
            view
        } else convertView
    }
}

interface ListItem<T> {
    fun createTextView(ui: AnkoContext<ListItemAdapter<T>>, init: _LinearLayout.() -> Unit): View

    fun createView(ui: AnkoContext<ListItemAdapter<T>>) = createTextView(ui) {
        gravity = Gravity.CENTER_VERTICAL
        padding = ui.dip(20)
    }

    fun getHolder(convertView: View): Holder<T> {
        return (convertView.tag as? Holder<T>) ?: Holder(convertView as T).apply {
            convertView.tag = this
        }
    }

    fun apply(convertView: View)

    class Holder<out T>(val view: T)
}