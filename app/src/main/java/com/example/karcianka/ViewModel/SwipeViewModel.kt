package com.example.karcianka.ViewModel

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.karcianka.Model.SwipeRightCardModel
import com.example.karcianka.Model.SwipeRightModel

class SwipeViewModel(application: Application): AndroidViewModel(application) {

    //Card swipe
    private val stream = MutableLiveData<SwipeRightModel>()

    val modelStream: LiveData<SwipeRightModel>
        get() = stream
    private val data: List<SwipeRightCardModel>
    private var currentIndex: Int

    private val topCard: SwipeRightCardModel
    private val bottomCard: SwipeRightCardModel

    init {
        data = listOf(
            SwipeRightCardModel(backgroundColor = Color.parseColor("#0000FFFF")),
            SwipeRightCardModel(backgroundColor = Color.parseColor("#0000FFFF")),
            SwipeRightCardModel(backgroundColor = Color.parseColor("#0000FFFF")),
            SwipeRightCardModel(backgroundColor = Color.parseColor("#0000FFFF"))
        )
        currentIndex = 0
        topCard = data[currentIndex % data.size]
        bottomCard = data[(currentIndex + 1) % data.size]
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