package com.example.karcianka

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.example.karcianka.database.All
import com.example.karcianka.database.All.Companion.wszystkielokacje
import com.example.karcianka.database.Location
import kotlinx.android.synthetic.main.fragment_card.*
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
            All.wieza.locs = mutableListOf(All.elektryk, All.hala, All.labirynt)
            All.chemia.locs = mutableListOf(All.gig, All.labirynt, All.klodnica)
            All.labirynt.locs = mutableListOf(
                All.wieza,
                All.elektryk,
                All.chemia,
                All.hala,
                All.biblioteka
            )
            All.hala.locs = mutableListOf(All.wieza, All.labirynt, All.biblioteka)
            All.biblioteka.locs = mutableListOf(All.ms, All.gig, All.chemia, All.hala, All.klodnica)
            All.ms.locs = mutableListOf(All.biblioteka)
            All.klodnica.locs = mutableListOf(All.chemia, All.biblioteka, All.park, All.mt)
            All.mt.locs = mutableListOf(All.chemia, All.cek)
            All.cek.locs = mutableListOf(All.mt)
            All.park.locs = mutableListOf(All.klodnica)
        }

        //znajdz lokacje po drawable
        fun FindLocByDraw(const: Drawable.ConstantState): Location
        {
            for(loc in All.wszystkielokacje)
            {
                println(loc)
            }
            return Location()
        }

    }
}
