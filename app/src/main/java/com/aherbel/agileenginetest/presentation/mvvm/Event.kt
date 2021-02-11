package com.reservando.android.presentation.mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

open class Event<out T>(private val content: T) {
    
    companion object {
        
        val EMPTY get() = Event(Unit)
    }
    
    var hasBeenHandled = false
        private set
    
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
    
    fun peekContent(): T = content
}

typealias EventLiveData<T> = LiveData<Event<T>>

inline fun <T> EventLiveData<T>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit,
) {
    observe(owner, { event ->
        event?.getContentIfNotHandled()?.let(onEventUnhandledContent)
    })
}