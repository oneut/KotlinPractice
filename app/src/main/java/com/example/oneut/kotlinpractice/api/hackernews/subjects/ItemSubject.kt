package com.example.oneut.kotlinpractice.api.hackernews.subjects

import android.content.Context
import com.example.oneut.kotlinpractice.api.hackernews.Observables
import com.firebase.client.Firebase
import io.reactivex.subjects.PublishSubject
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemSubject @Inject constructor() {
    fun create(context: Context, callback: (HashMap<String, String>) -> Unit): PublishSubject<Long> {
        Firebase.setAndroidContext(context)

        val subject: PublishSubject<Long> = PublishSubject.create()
        val hackerNewsObservable = Observables()

        subject.flatMap { id ->
            hackerNewsObservable.getItem(id)
        }.subscribe(callback)

        return subject
    }
}