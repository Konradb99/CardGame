package com.example.karcianka.GameEntity

import com.example.karcianka.R

class Item(override var id: String = "",
               override var name: String="",
               override var description: String="",
               override var draw:Int = R.drawable.blank): ICard
{
    init {

    }
}