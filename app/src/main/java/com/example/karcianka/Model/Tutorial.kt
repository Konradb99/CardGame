package com.example.karcianka.Model

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.karcianka.GameEntity.All
import com.example.karcianka.Model.LocNav.Companion.SetCard
import com.example.karcianka.R
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.GameViewModel

class Tutorial() {


    companion object {
        public var shots = 0;

        fun EnterSolarisSamouczek(
            context: Context,
            front: TextView,
            back: LinearLayout,
            card_back_text: TextView,
            card_back_title: TextView,
            cardVM: CardViewModel,
            gameVM: GameViewModel
        )
        {
            //wlaczenie Kolegi
            SetCard(front,card_back_text, card_back_title, All.kolega)
            cardVM.card_back_text.text=All.kolega.description+"\n\n"+"(by zaczac rozmowę, przeciągnij kartę w prawo.)\n"+gameVM.checkpoint
            cardVM.updateCard(front, back, card_back_text, card_back_title)


        }

        fun EnterMinisterstwoSamouczek(
            view: View,
            front: TextView,
            back: LinearLayout,
            card_back_text: TextView,
            card_back_title: TextView,
            cardVM: CardViewModel,
            gameVM: GameViewModel
        )
        {

            println("----> EnterMinisterstwo")
            //wlaczenie Kolegi
            SetCard(front,card_back_text, card_back_title, All.kolega)
            cardVM.card_back_text.text=All.kolega.description+"\n\n" +
                    "No dobra! Jaki kolorek tym razem?\n\n" +
                    "Wiesz co,      A jakie sa"+
                    "jednak nie     dzisiaj" +
                    "dzis...        opcje?\n\n"+gameVM.checkpoint
            cardVM.updateCard(front, back, card_back_text, card_back_title)
        }
    }
}