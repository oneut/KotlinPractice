package com.example.oneut.kotlinpractice.api.hackernews.subjects

import android.content.Context
import com.example.oneut.kotlinpractice.api.hackernews.Observables
import com.firebase.client.Firebase
import io.reactivex.subjects.PublishSubject
import io.reactivex.schedulers.Schedulers
import java.util.HashMap

class TopStoriesSubject {

    fun create(context: Context, callback: (HashMap<String, String>) -> Unit): PublishSubject<Int> {
        Firebase.setAndroidContext(context)

        val subject: PublishSubject<Int> = PublishSubject.create()
        val hackerNewsObservable = Observables()

        subject
                .switchMap { page ->
                    hackerNewsObservable.getTopStoris(page)
                }
                .flatMapIterable { list ->
                    list
                }
                .observeOn(Schedulers.computation())
                .flatMap { id ->
                    System.out.println("map: (" + Thread.currentThread().name + ")")
                    hackerNewsObservable.getItem(id)
                }.subscribe(callback)

        return subject
    }
}