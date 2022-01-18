package com.example.karcianka.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.AndroidViewModel
import com.example.karcianka.Database.CardDatabase
import com.example.karcianka.Database.DAO
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.FlipModel
import com.example.karcianka.GameEntity.Location
import com.example.karcianka.Model.Game
import com.example.karcianka.Model.LocNav
import com.example.karcianka.R

class GameViewModel(application: Application, private var cardVM: CardViewModel, private val context: Context): AndroidViewModel(application) {
    private val dao : DAO
    public var checkpoint: Int
    public var game: Game
    init{
        dao = CardDatabase.getInstance(application).dao
        checkpoint = 0;
        game = Game()
    }

    fun checkpoints(motionLayout: MotionLayout, pass: Int, like: Int) {
        when (motionLayout.getCurrentState()) {
            R.id.pass -> {
                Toast.makeText(context, "W Lewo!", Toast.LENGTH_SHORT).show();
            }
            R.id.like -> {
                Toast.makeText(context, "W Prawo!", Toast.LENGTH_SHORT).show();
            }
        }

        when (this.checkpoint) {

            0 -> {

                //dopoki nie przesunie w prawo
                when (motionLayout.getCurrentState()) {
                    R.id.pass -> {

                    }
                    R.id.like -> {
                        checkpoint++
                        cardVM.FlipBack()
                        cardVM.card_back_text.text = "Idziemy sie napić?\n\n\n\n\n NIE                  TAK";

                    }
                }


            }
            1 -> {
                when (motionLayout.getCurrentState()) {
                    R.id.pass -> {

                        println("\nnie jestes studentem.")
                        //  checkpoint += 2;
                        LocNav.SetCard(
                            cardVM.card_front,
                            cardVM.card_back_text,
                            cardVM.card_back_title,
                            All.blankloc
                        )
                        cardVM.card_back_text.text = "I ty smiesz się nazywać studentem?";
                        cardVM.card_back_title.text = "KONIEC GRY"




                    }
                    R.id.like -> {
                        //  checkpoint++;
                        println("\nidziemy pic")

                    }
                }
            }
            2 -> { //NIE JESTES STUDENTEM


            }
            3 -> {//idziemy pic
            }

        }
    }
}
