package com.example.karcianka.GameEntity

import com.example.karcianka.R


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