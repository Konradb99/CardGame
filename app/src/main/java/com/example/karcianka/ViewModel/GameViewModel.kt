package com.example.karcianka.ViewModel

import android.app.Application
import android.content.Context
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.AndroidViewModel
import com.example.karcianka.Database.CardDatabase
import com.example.karcianka.Database.DAO
import com.example.karcianka.GameEntity.All
import com.example.karcianka.Model.Game
import com.example.karcianka.Model.LocNav
import com.example.karcianka.Model.Tutorial
import com.example.karcianka.R

class GameViewModel(application: Application, private var cardVM: CardViewModel, private val context: Context): AndroidViewModel(application) {
    private val dao : DAO
    public var checkpoint: String
    public var game: Game

    init{
        dao = CardDatabase.getInstance(application).dao
        game = Game()
        checkpoint = game.checkpoints;

    }

    fun checkpoints(motionLayout: MotionLayout, pass: Int, like: Int) {
        when (motionLayout.getCurrentState()) {
            R.id.left -> {
                Toast.makeText(context, "W Lewo!", Toast.LENGTH_SHORT).show();
            }
            R.id.right -> {
                Toast.makeText(context, "W Prawo!", Toast.LENGTH_SHORT).show();
            }
        }

        when (this.checkpoint) {

            "01" -> {

                //dopoki nie przesunie w prawo
                when (motionLayout.getCurrentState()) {
                    R.id.left -> {
                        checkpoint+="1"
                        cardVM.FlipBack()
                        LocNav.SetCard(
                            cardVM.card_front,
                            cardVM.card_back_text,
                            cardVM.card_back_title,
                            All.kolega
                        )
                        LocNav.AddChoice(cardVM.card_back_text, "A daj spokój z przywitaniem. Idziemy sie napić?",
                            "Nie dzisiaj.","Pewnie!" )

                        cardVM.card_back_text.text =cardVM.card_back_text.text.toString()+ "\n\n"+checkpoint;

                    }
                    R.id.right -> {
                        checkpoint+="1"
                        cardVM.FlipBack()
                        LocNav.SetCard(
                            cardVM.card_front,
                            cardVM.card_back_text,
                            cardVM.card_back_title,
                            All.kolega
                        )
                        LocNav.AddChoice(cardVM.card_back_text, "A daj spokój z przywitaniem. Idziemy sie napić?",
                            "Nie dzisiaj.","Pewnie!" )

                        cardVM.card_back_text.text =cardVM.card_back_text.text.toString()+ "\n\n"+checkpoint;

                    }
                }


            }
            "011" -> {
                when (motionLayout.getCurrentState()) {
                    R.id.left -> {
                        checkpoint+="0"
                        println("\nnie jestes studentem.\n"+ checkpoint)

                        LocNav.SetCard(
                            cardVM.card_front,
                            cardVM.card_back_text,
                            cardVM.card_back_title,
                            All.blankloc
                        )
                        cardVM.card_back_text.text = "I ty smiesz się nazywać studentem?\n"+checkpoint;
                        cardVM.card_back_title.text = "KONIEC GRY"




                    }
                    R.id.right -> {
                        checkpoint+="1"
                        cardVM.FlipFront()
                        LocNav.SetCard(cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title, All.ministerstwo)
                        cardVM.card_back_text.text =All.ministerstwo.description+ "\n\n(musimy wejsc do ministerstwa. Pamiętasz jeszcze, jak?)" +
                                "\n"+checkpoint
                        //0111



                    }
                }
            }
            "01111"  -> {
                //idziemy pic
                when (motionLayout.getCurrentState()) {

                    R.id.right -> {
                        println("idziemy pic.")
                    }
                    R.id.left -> {
                        checkpoint+="0"
                        println("\nnie jestes studentem.\n"+ checkpoint)

                        LocNav.SetCard(
                            cardVM.card_front,
                            cardVM.card_back_text,
                            cardVM.card_back_title,
                            All.blankloc
                        )
                        cardVM.card_back_text.text = "I ty smiesz się nazywać studentem?\n"+checkpoint;
                        cardVM.card_back_title.text = "KONIEC GRY"
                    }
                }

            }
        }
    }
}
