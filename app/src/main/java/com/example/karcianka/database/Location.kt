package com.example.karcianka.database

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.example.karcianka.R
import android.view.View


class Location(override var id: String = "",
               override var name: String="",
               override var description: String="",
               var npcs: MutableCollection<NPC> = mutableListOf<NPC>(),
               var locs: MutableCollection<Location> = mutableListOf<Location>(),
                override var draw:Int = R.drawable.blank
): ICard
{
    init {

        npcs = mutableListOf<NPC>();
        locs = mutableListOf<Location>()
    }
}