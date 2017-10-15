package com.example.oneut.kotlinpractice.api.hackernews

import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import io.reactivex.Observable

class Observables {
    private val ref = Firebase("https://hacker-news.firebaseio.com/v0")

    fun getTopStoris(page: Int = 1, limit: Int = 10): Observable<List<Long>> {
        return Observable.create { e ->
            ref.child("/topstories").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val from = limit * (page - 1)
                    val to = from + limit
                    e.onNext((snapshot.value as List<Long>).subList(from, to))
                    e.onComplete()
                }

                override fun onCancelled(error: FirebaseError) {
                    e.onComplete()
                    System.out.println(error.message)
                }
            })
        }
    }

    fun getItem(id: Long): Observable<HashMap<String, String>> {
        return Observable.create { e ->
            ref.child("/item/" + id.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    e.onNext(snapshot.value as HashMap<String, String>)
                    e.onComplete()
                }

                override fun onCancelled(error: FirebaseError) {
                    e.onComplete()
                }
            })
        }
    }
}