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
import com.example.karcianka.GameEntity.FlipModel
import com.example.karcianka.GameEntity.Location
import com.example.karcianka.Model.Game
import com.example.karcianka.Model.LocNav
import com.example.karcianka.Model.Tutorial
import com.example.karcianka.R
import java.util.*

class GameViewModel(application: Application, private var cardVM: CardViewModel, private val context: Context): AndroidViewModel(application) {
    private val dao : DAO
    public var checkpoint: String
    public var game: Game
    public var walking : Boolean;
    public var thisloc :Location;
    public var locleft : Location;
    public var locright:Location;

    init{
        dao = CardDatabase.getInstance(application).dao
        game = Game()
        checkpoint = game.checkpoints;
        walking = false;
        thisloc= Location();
        locleft= Location();
        locright= Location();
        checkpoint="0111"// 0111 to start from ministerstwo

    }

    fun checkpoints(motionLayout: MotionLayout, pass: Int, like: Int) {
        if(walking) {


            when (motionLayout.getCurrentState()) {
                R.id.left -> {
              //      Toast.makeText(context, "W Lewo!", Toast.LENGTH_SHORT).show();
                    thisloc=locleft;
                    LocNav.SetLoc(cardVM.card_front, cardVM.card_back_text,
                        cardVM.card_back_title, thisloc)
                    locleft = LocNav.GetRandomExit(thisloc);
                    locright = LocNav.GetRandomExit(thisloc);

                    if(thisloc.locs.size!=0 && thisloc.locs.size!=1)
                    {
                        while(locright==locleft)
                        {
                            locright = LocNav.GetRandomExit(thisloc);
                        }
                    }
                    LocNav.AddChoice(cardVM.card_back_text,
                        left = locleft.name, right = locright.name )


                }
                R.id.right -> {
             //       Toast.makeText(context, "W Prawo!", Toast.LENGTH_SHORT).show();
                    thisloc = locright;
                    LocNav.SetLoc(cardVM.card_front, cardVM.card_back_text,
                        cardVM.card_back_title, thisloc)
                    locleft = LocNav.GetRandomExit(thisloc);
                    locright = LocNav.GetRandomExit(thisloc);

                    if(thisloc.locs.size!=0 && thisloc.locs.size!=1)
                    {
                        while(locright==locleft)
                        {
                            locright = LocNav.GetRandomExit(thisloc);
                        }
                    }
                    LocNav.AddChoice(cardVM.card_back_text,
                        left = locleft.name, right = locright.name)
                }

            }

            if(checkpoint=="011" && thisloc == All.ministerstwo) {
                LocNav.SetLoc(cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title, thisloc)

                LocNav.AddChoice(
                    cardVM.card_back_text, "To tu! W końcu... Wejdźmy tu.",
                    locleft.name, locright.name, afteradd = checkpoint
                )
                checkpoint+="1" //czyli 0111
            }

        }
        else
        {
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
                                "Nie dzisiaj.","Pewnie! Spotkajmy sie w Ministerstwie." )

                            cardVM.card_back_text.text =cardVM.card_back_text.text.toString()+ "\n\n"+checkpoint;

                            //walking=true;
                            //thisloc = All.solaris;


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
                                "Nie dzisiaj.","Pewnie! Spotkajmy sie w Ministerstwie." )

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

                            //   checkpoint+="1"
                            thisloc = All.solaris
                            walking=true;
                            LocNav.SetLoc(cardVM.card_front, cardVM.card_back_text,
                                cardVM.card_back_title, thisloc)
                            locleft = LocNav.GetRandomExit(thisloc);
                            locright = LocNav.GetRandomExit(thisloc);

                            if(thisloc.locs.size>1)
                            {
                                while(locright==locleft)
                                {
                                    locright = LocNav.GetRandomExit(thisloc);
                                }
                            }
                            LocNav.AddChoice(cardVM.card_back_text,"Musimy dotrzec do Ministerstwa. Przesuwaj kartę w prawo lub lewo, by się przemieszczać.",
                                left = locleft.name, right = locright.name )



                        }
                    }
                }

                "1"  -> {
                    //idziemy pic
                    when (motionLayout.getCurrentState()) {

                        R.id.right -> {
                            checkpoint+="1"

                            //wlaczenie Kolegi
                            LocNav.SetCard(cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title, All.kolega)
                            cardVM.FlipFront_instant()
                            LocNav.AddChoice(cardVM.card_back_text,"No dobra! Co tym razem do picia?",
                                "To od czego zaczynamy?", "Dawaj pierwszy shot z brzegu.")

                            cardVM.card_back_text.text= cardVM.card_back_text.text.toString()+"\n\n"+checkpoint


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
                "11"  -> {

                    //kolejne shoty -> numeracja kolejnych shotów jest w Tutorial.shots
                    if(Tutorial.shots!=5) {

                        var it = rand(1, 5)
                        when (it) {
                            1->{
                                LocNav.SetCard(
                                    cardVM.card_front,
                                    cardVM.card_back_text,
                                    cardVM.card_back_title,
                                    All.shot1)

                            }
                            2->
                            {
                                LocNav.SetCard(
                                    cardVM.card_front,
                                    cardVM.card_back_text,
                                    cardVM.card_back_title,
                                    All.shot2)
                            }
                            3->
                            {
                                LocNav.SetCard(
                                    cardVM.card_front,
                                    cardVM.card_back_text,
                                    cardVM.card_back_title,
                                    All.shot3)
                            }
                            4->
                            {
                                LocNav.SetCard(
                                    cardVM.card_front,
                                    cardVM.card_back_text,
                                    cardVM.card_back_title,
                                    All.shot4)
                            }
                            5->{
                                LocNav.SetCard(
                                    cardVM.card_front,
                                    cardVM.card_back_text,
                                    cardVM.card_back_title,
                                    All.shot5)
                            }
                        }
                        cardVM.FlipFront_instant()
                        LocNav.AddChoice(cardVM.card_back_text, "Pijesz go?", "Nie, dawaj inny,",
                            "Oczywiście, że tak. A potem inny.")
                    }
                    else
                        checkpoint+="5"

                    when (motionLayout.getCurrentState()) {

                        R.id.right -> {
                            Tutorial.shots++;
                        }
                        R.id.left -> {
                        }
                    }
                }

                "115" ->
                {
                    println(";D ;D ;D ;D Shoty wypite.")
                    Toast.makeText(context, "Jestes dość pijany!", Toast.LENGTH_SHORT).show();

                    cardVM.FlipFront();
                    LocNav.SetCard(cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title, All.kolega)
                    LocNav.AddChoice(cardVM.card_back_text, "Co? Juz masz dosc? Odprowadzić cię?",
                        "MoŻe Mi SiĘ PoMoC PrZyDać...",
                        "Nieeee, dam radę. Bawcie sie dobrze.")

                }

            }
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }
}