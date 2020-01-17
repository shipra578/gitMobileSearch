package com.shipra.android.gitmobilesearch.util

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class GetRxObservableFromView {

    companion object {
        fun fromView(searchView: SearchView, progress: ProgressBar): Observable<String> {
            val subject = PublishSubject.create<String>()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    subject.onComplete()
                    progress.visibility = View.VISIBLE
                    return true
                }

                override fun onQueryTextChange(text: String): Boolean {
                    subject.onNext(text)
                    return true
                }
            })
            return subject
        }
    }
}