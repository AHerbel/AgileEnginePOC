package com.reservando.android.presentation.mvvm

import androidx.lifecycle.MutableLiveData

open class MutableEventLiveData<T> : MutableLiveData<Event<T>>() {
    fun sendEvent(content: T) {
        value = Event(content)
    }
}
