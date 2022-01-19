package com.example.karcianka.Model

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.karcianka.GameEntity.All
import com.example.karcianka.Model.LocNav.Companion.AddChoice
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

            AddChoice(card_back_text,left="O, hej!",right= "Dawno sie nie widzielismy!",
                afteradd =  "(by zaczac rozmowę, przeciągnij kartę w którąś stronę.)")

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
            gameVM.walking=false;


            LocNav.SetCard(cardVM.card_front, cardVM.card_back_text,
                cardVM.card_back_title, All.blankloc)
            cardVM.FlipFront_instant()
            card_back_text.text= All.ministerstwo.description;

            AddChoice(card_back_text,"No dobra! Co tym razem do picia?",
                "Jednak nie dzisiaj...", "Dobra, gdzie on jest?")

            card_back_text.text= card_back_text.text.toString()+"\n\n"+gameVM.checkpoint
            cardVM.updateCard(front, back, card_back_text, card_back_title)
        }
    }
}