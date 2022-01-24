package com.example.karcianka.Model

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.All.Companion.all
import com.example.karcianka.GameEntity.All.Companion.wszystkielokacje
import com.example.karcianka.GameEntity.ICard
import com.example.karcianka.GameEntity.Location
import com.example.karcianka.R
import kotlin.random.Random

class LocNav
{
    companion object
    {

        //wybierz losowo lokacje wyjściową
        fun GetRandomExit(loc: Location):Location
        {
            var index: Int = Random.nextInt((loc.locs.size - 1) - 0 + 1) + 0;
            return loc.locs.elementAt(index)
        }

        //zwraca dwa losowe przejścia (różne, jeśli jest możliwe więcej niż jedno) jako tablica Location {lewy, prawy}
        //oraz pokazuje je jako prawo-lewo strzałeczki
        fun GetBothExits(thisloc: Location, card_front : TextView,
                         card_back_text : TextView, card_back_title: TextView) : Array<Location>
        {

            SetLoc(thisloc, card_front, card_back_text,
                card_back_title)
            var locleft= GetRandomExit(thisloc);
            var locright = GetRandomExit(thisloc);

            if(thisloc.locs.size!=0 && thisloc.locs.size!=1)
                while(locright==locleft)
                    locright = GetRandomExit(thisloc);

            AddChoice(card_back_text, left = locleft.name, right = locright.name)

            return arrayOf(locleft,locright)
        }

        //zainicjuj graf
        fun ConnectLocGraph()
        {
            All.gig.locs = mutableListOf(All.elektryk, All.chemia,
                All.biblioteka, All.ministerstwo)
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
            All.ministerstwo.locs = mutableListOf(All.gig)
        }

        //znajduje lokacje po drawable
        fun GetCurrentLoc(current_loc: View) :Location
        {
            var emptyloc = Location()
            for(loc in wszystkielokacje)
                if(loc.draw == current_loc.getTag())
                    return loc;

            return emptyloc
        }

        fun GetCurrentCard(current_loc: View) :ICard
        {
            var emptyloc = Location()
            for(loc in all)
                if(loc.draw == current_loc.getTag())
                    return loc;

            return emptyloc
        }

        //ustawia kartę lokacji na obecny widok (przydaje sie zamiast SetLoc, gdy przechodzimy do grafu
        //i poruszania się
        fun SetLoc(loc :Location, card_front : TextView, card_back_text : TextView, card_back_title: TextView)
        {
            card_front.setTag(loc.draw)
            card_back_text.text = loc.description
            card_back_title.text = loc.name
            card_front.setBackgroundResource(loc.draw)
        }

        //ustawia konkretną kartę na obecny widok (dowolną, bo korzysta z ICard)
        fun SetCard(icard: ICard, card_front : TextView, card_back_text : TextView, card_back_title: TextView)
        {
            card_front.setTag(icard.draw)
            card_back_text.text = icard.description
            card_back_title.text = icard.name
            card_front.setBackgroundResource(icard.draw)
        }

        //dodaje dopiski i wybór opcji na obecnym "tyle karty"
        fun AddChoice(card_back_text : TextView, beforeadd:String="",
                      left:String, right: String, afteradd :String ="")
        {
            var text ="";
            if(beforeadd!="")
                text+="\n\n\n"+beforeadd;


            text += "\n\n"+
                    "<-------\n"+
                    left+"\n\n"+
                    "------->\n"+
                    right+"\n"
            if(afteradd!="")
                text+="\n\n"+afteradd;

            card_back_text.text = card_back_text.text.toString() + text;
        }


        //Get current location

        fun GetMap(front: TextView): Int {
            var curLoc = GetCurrentCard(front)

            if(curLoc in All.shots){
                return R.drawable.mapa
            }
            else{
                if(curLoc in All.wszystkielokacje){
                    when (curLoc){
                        All.hala -> return R.drawable.mapa_hala
                        All.gig -> return R.drawable.mapa_gig
                        All.firewall -> return R.drawable.mapa_park
                        All.elektryk -> return R.drawable.mapa_elektryk
                        All.chemia -> return R.drawable.mapa_chemia
                        All.cek -> return R.drawable.mapa_cek
                        All.biblioteka -> return R.drawable.mapa_biblioteka
                        All.wnetrzeMinisterstwo -> return R.drawable.mapa
                        All.klodnica -> return R.drawable.mapa_mt
                        All.labirynt -> return R.drawable.mapa_labirynt
                        All.ministerstwo -> return R.drawable.mapa
                        All.mrowisko -> return R.drawable.mapa
                        All.ms -> return R.drawable.mapa_ms
                        All.mt -> return R.drawable.mapa_mt
                        All.wieza -> return R.drawable.mapa_aei
                        All.narnia -> return R.drawable.mapa_aei
                        All.park -> return R.drawable.mapa_park
                        All.samouczek -> return R.drawable.mapa_solaris
                        All.solaris -> return R.drawable.mapa_solaris
                    }
                }
                return R.drawable.mapa
            }
        }
    }
}