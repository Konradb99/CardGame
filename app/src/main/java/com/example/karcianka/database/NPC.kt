package com.example.karcianka.database

import android.graphics.drawable.Drawable
import com.example.karcianka.R

class NPC(override var id: String = "", override var name: String="", override var description: String="",
          var stats: Map<String, Int> = mapOf(), override var draw:Int = R.drawable.blank,
            var item :Item = Item()): ICard
{
    init {
        stats = mapOf("strength" to 0, "dexterity" to 0,
            "inteligence" to 0, "condition" to 0)

    }
}