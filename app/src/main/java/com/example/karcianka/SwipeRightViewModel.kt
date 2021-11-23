package com.example.karcianka

import android.graphics.Color
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class SwipeRightViewModel:ViewModel() {


    //Card swipe
    private val stream = MutableLiveData<SwipeRightModel>()

    val modelStream: LiveData<SwipeRightModel>
        get() = stream

    private val data = listOf(
        SwipeRightCardModel(backgroundColor = Color.parseColor("#FFFFFF")),
        SwipeRightCardModel(backgroundColor = Color.parseColor("#FFFFFF")),
        SwipeRightCardModel(backgroundColor = Color.parseColor("#FFFFFF")),
        SwipeRightCardModel(backgroundColor = Color.parseColor("#FFFFFF"))
    )
    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]
    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    init {
        updateStream()
    }

    fun swipe() {
        currentIndex += 1
        updateStream()
    }

    private fun updateStream() {
        stream.value = SwipeRightModel(
            top = topCard,
            bottom = bottomCard
        )
    }


}