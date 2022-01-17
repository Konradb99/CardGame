package com.example.karcianka.Model

import android.view.View
import android.widget.TextView
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.All.Companion.wszystkielokacje
import com.example.karcianka.GameEntity.ICard
import com.example.karcianka.GameEntity.Location
import kotlin.random.Random

class LocNav
{
    init
    {
        ConnectLocGrapf()
    }

    companion object
    {

        //wybierz losowo lokacje wyjściową
        fun GetRandomExit(loc: Location):Location
        {
            var rand = Random   //Generowanie pseudolosowosci wyboru
            var index: Int = rand.nextInt((loc.locs.size - 1) - 0 + 1) + 0;

            return loc.locs.elementAt(index)
        }

        //zainicjuj graf
        fun ConnectLocGrapf()
        {
            All.gig.locs = mutableListOf(All.elektryk, All.chemia, All.biblioteka)
            All.elektryk.locs = mutableListOf(All.gig, All.wieza, All.labirynt)
            All.wieza.locs = mutableListOf(All.elektryk, All.hala, All.labirynt, All.solaris)
            All.chemia.locs = mutableListOf(All.gig, All.labirynt, All.klodnica)
            All.labirynt.locs = mutableListOf(
                All.wieza,
                All.elektryk,
                All.chemia,
                All.hala,
                All.biblioteka
            )
            All.hala.locs = mutableListOf(All.wieza, All.labirynt, All.biblioteka, All.solaris)
            All.biblioteka.locs = mutableListOf(All.ms, All.gig, All.hala, All.klodnica)
            All.ms.locs = mutableListOf(All.biblioteka)
            All.klodnica.locs = mutableListOf(All.chemia, All.biblioteka, All.park, All.mt)
            All.mt.locs = mutableListOf(All.chemia, All.cek)
            All.cek.locs = mutableListOf(All.mt)
            All.park.locs = mutableListOf(All.klodnica)
            All.solaris.locs = mutableListOf(All.wieza, All.hala)
        }

        //znajdz lokacje po drawable
        fun GetNextLoc(current_loc: View): Location
        {
            var next_loc = Location()
            println("")
            println("============Possible locations: ================")
            for(loc in wszystkielokacje)
            {
                if(loc.draw == current_loc.getTag()){
                    println("Current loc: " + loc.draw)

                    for(possible_loc in loc.locs){
                        println("Possible locs: " + possible_loc.draw)
                    }

                    var rand = Random   //Generowanie pseudolosowosci wyboru
                    var index: Int = rand.nextInt((loc.locs.size - 1) - 0 + 1) + 0;

                    next_loc = loc.locs.elementAt(index)
                    println(next_loc::class.qualifiedName)
                    println("Generated next loc: " + loc.locs.elementAt(index).draw)
                    break
                }
            }
            println("===============================================")
            println("")
            return next_loc
        }

        fun GetCurrentLoc(current_loc: View) :Location
        {
            var emptyloc = Location()
            println("")
            println("============Possible locations: ================")
            for(loc in wszystkielokacje)
            {
                if(loc.draw == current_loc.getTag()){
                    return loc;
                }
            }
            println("===============================================")
            println("")
            return emptyloc
        }

        fun SetLoc(card_front : TextView, card_back_text : TextView, card_back_title: TextView, loc :Location)
        {
            card_front.setTag(loc.draw)
            card_back_text.text = loc.description
            card_back_title.text = loc.name
            card_front.setBackgroundResource(loc.draw)
        }

        fun SetCard(card_front : TextView, card_back_text : TextView, card_back_title: TextView, icard: ICard)
        {
            card_front.setTag(icard.draw)
            card_back_text.text = icard.description
            card_back_title.text = icard.name
            card_front.setBackgroundResource(icard.draw)
        }

    }
}
