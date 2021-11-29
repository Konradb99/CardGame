package com.example.karcianka

import android.app.PendingIntent.getActivity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.karcianka.database.All
import com.example.karcianka.database.All.Companion.wszystkielokacje
import com.example.karcianka.database.Location
import kotlinx.android.synthetic.main.fragment_card.*
import kotlin.random.Random
import kotlin.reflect.typeOf

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

        fun GetCurrentLoc(current_loc: View): Location
        {
            var location = Location()
            for(loc in wszystkielokacje){
                if(loc.draw == current_loc.getTag()){
                    location = loc
                }
            }
            return location
        }
    }
}
