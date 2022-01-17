package com.example.karcianka.Model

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.All.Companion.kolega
import com.example.karcianka.GameEntity.Flip
import com.example.karcianka.Model.LocNav.Companion.SetCard
import com.example.karcianka.Model.LocNav.Companion.SetLoc

class Tutorial() {
    companion object {

        fun EnterSolarisSamouczek(
            context: Context,
            front: TextView,
            card_back_text: TextView,
            card_back_title: TextView
        )
        {

            //wlaczenie Kolegi
            SetCard(front,card_back_text, card_back_title, All.kolega)

        }
    }
}