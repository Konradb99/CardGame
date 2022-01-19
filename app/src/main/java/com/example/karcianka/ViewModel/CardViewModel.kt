package com.example.karcianka.ViewModel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import com.example.karcianka.Database.CardDatabase
import com.example.karcianka.Database.DAO
import com.example.karcianka.GameEntity.FlipModel
import com.example.karcianka.Model.LocNav
import org.w3c.dom.Text

class CardViewModel(
    application: Application,
    public var context: Context
): AndroidViewModel(application) {
    private val dao : DAO
    public var card_front: TextView
    public var card_back_text: TextView
    public var card_back_title: TextView
    public var card_back: LinearLayout
    public var gameNotStarted: Int
    init{
        dao = CardDatabase.getInstance(application).dao
        gameNotStarted = 1
        card_front = TextView(context)
        card_back_title = TextView(context)
        card_back_text = TextView(context)
        card_back = LinearLayout(context)
    }
    fun Flip(){//obraca jakkolwiek
        FlipModel.Animate(context, card_front, card_back)
    }
    fun FlipBack(){//zmienia na tył
        FlipModel.AnimateBack(context, card_front, card_back)
    }

    fun FlipFront()//zmienia na front
    {
        FlipModel.AnimateFront(context, card_front, card_back)
    }
    fun updateCard(front: TextView, back: LinearLayout, backText: TextView, backTitle: TextView){
        card_front = front
        card_back = back
        card_back_title = backTitle
        card_back_text = backText
        println(LocNav.GetCurrentLoc(card_front))
    }
}