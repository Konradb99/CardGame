package com.example.karcianka.ViewModel

import android.app.Application
import android.content.Context
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.karcianka.Database.CardDatabase
import com.example.karcianka.Database.DAO
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.Database.Entity.GameSave
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.FlipModel
import com.example.karcianka.GameEntity.Location
import com.example.karcianka.Model.Game
import com.example.karcianka.Model.LocNav
import com.example.karcianka.Model.Tutorial
import com.example.karcianka.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class GameViewModel(application: Application, private var cardVM: CardViewModel, private var eqVM: EquipmentViewModel, private val context: Context): AndroidViewModel(application) {
    private val dao : DAO
    public var checkpoint: String
    public var game: Game
    public var walking : Boolean;
    public var thisloc :Location;
    public var locleft : Location;
    public var locright:Location;
    private var item: EquipmentItems
    private var i = 0
    public var gameSaves: LiveData<List<GameSave>>

    init{
        dao = CardDatabase.getInstance(application).dao
        game = Game()
        checkpoint = game.checkpoints;
        walking = false;
        thisloc= Location();
        locleft= Location();
        locright= Location();
        item = EquipmentItems(0, 0, "", R.drawable.eq_back, R.drawable.eq_back)
        checkpoint="0"
        gameSaves = dao.GetAllSaves()

    }

    fun checkpoints(motionLayout: MotionLayout, pass: Int, like: Int) {
        if(walking) {
            when (motionLayout.getCurrentState()) {
                R.id.left -> {
                    thisloc=locleft;
                    var arr = LocNav.GetBothExits(thisloc,
                        cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title)
                    locleft = arr[0];
                    locright= arr[1]

                }
                R.id.right -> {
             //       Toast.makeText(context, "W Prawo!", Toast.LENGTH_SHORT).show();
                    thisloc = locright;
                    var arr = LocNav.GetBothExits(thisloc,
                        cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title)
                    locleft = arr[0];
                    locright=arr[1]
                }
            }

            //czyli znalezienie ministerstwa w samouczku
            if(checkpoint=="011" && thisloc == All.ministerstwo) {
                LocNav.SetLoc(thisloc,cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title)

                LocNav.AddChoice(
                    cardVM.card_back_text, "To tu! W ko??cu... Wejd??my tu.",
                    locleft.name, locright.name, afteradd = checkpoint
                )
                checkpoint+="1" //czyli 0111
            }
        }
        else
        {
            when (this.checkpoint) {

                "01" -> {
                    when (motionLayout.getCurrentState()) {
                        R.id.left -> {
                            checkpoint+="1"
                            cardVM.FlipBack()

                            LocNav.SetCard(
                                All.kolega,
                                cardVM.card_front,
                                cardVM.card_back_text,
                                cardVM.card_back_title)
                            LocNav.AddChoice(cardVM.card_back_text, "A daj spok??j z przywitaniem. Idziemy sie napi???",
                                "Nie dzisiaj.","Pewnie! Spotkajmy sie w Ministerstwie." )

                            cardVM.card_back_text.text =cardVM.card_back_text.text.toString()+ "\n\n"+checkpoint;

                        }
                        R.id.right -> {
                            checkpoint+="1"
                            cardVM.FlipBack()

                            LocNav.SetCard(
                                All.kolega,
                                cardVM.card_front,
                                cardVM.card_back_text,
                                cardVM.card_back_title)
                            LocNav.AddChoice(cardVM.card_back_text, "A daj spok??j z przywitaniem. Idziemy sie napi???",
                                "Nie dzisiaj.","Pewnie! Spotkajmy sie w Ministerstwie." )

                            cardVM.card_back_text.text =cardVM.card_back_text.text.toString()+ "\n\n"+checkpoint;

                        }
                    }


                }
                "011" -> {
                    when (motionLayout.getCurrentState()) {
                        R.id.left -> {
                            GameOver("I ty smiesz si?? nazywa?? studentem?", motionLayout);
                        }
                        R.id.right -> {//to jest prawie kopia LocNav.GetBothExits, ale musialo byc napisane tutaj
                            thisloc = All.solaris
                            walking=true;
                            LocNav.SetLoc(thisloc, cardVM.card_front, cardVM.card_back_text,
                                cardVM.card_back_title)
                            locleft = LocNav.GetRandomExit(thisloc);
                            locright = LocNav.GetRandomExit(thisloc);

                            if(thisloc.locs.size>1)
                                while(locright==locleft)
                                    locright = LocNav.GetRandomExit(thisloc);

                            LocNav.AddChoice(cardVM.card_back_text,"Musimy dotrzec do Ministerstwa. Przesuwaj kart?? w prawo lub lewo, by si?? przemieszcza??.",
                                left = locleft.name, right = locright.name )

                        }
                    }
                }

                "1"  -> {
                    //idziemy pic
                    when (motionLayout.getCurrentState()) {

                        R.id.right -> {
                            checkpoint+="1"
                            cardVM.FlipFront_instant()

                            cardVM.FlipFront_instant()

                            //wlaczenie Kolegi
                            LocNav.SetCard(All.kolega, cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title)
                            LocNav.AddChoice(cardVM.card_back_text,"No dobra! Co tym razem do picia?",
                                "To od czego zaczynamy?", "Dawaj pierwszy shot z brzegu.")

                            cardVM.card_back_text.text= cardVM.card_back_text.text.toString()+"\n\n"+checkpoint

                        }
                        R.id.left -> {
                            GameOver("I ty smiesz si?? nazywa?? studentem?", motionLayout);
                        }
                    }
                }

                "11"  -> {

                    //kolejne shoty -> numeracja kolejnych shot??w jest w Tutorial.shots
                    if(Tutorial.shots!=10) {

                        var it = rand(1, 5)
                        i = it - 1
                        LocNav.SetCard(All.shots[it-1], cardVM.card_front, cardVM.card_back_text,
                                    cardVM.card_back_title)

                        LocNav.AddChoice(cardVM.card_back_text, "Pijesz go?", "Nie, dawaj inny,",
                            "Oczywi??cie, ??e tak. A potem inny.")

                    }
                    else
                        checkpoint+="5"

                    when (motionLayout.getCurrentState()) {

                        R.id.right -> {
                            Tutorial.shots++;
                        }
                        R.id.left -> {
                            //Podniesienie kieliszka
                            item = EquipmentItems(0, 0, cardVM.card_back_title.text.toString(), All.shots[i].draw, All.shots[i].draw)
                            var exists = false
                            if(eqVM.allItems.value != null){
                                for(eqItem in eqVM.allItems.value!!){
                                    if(item.name == eqItem.name){
                                        exists = true
                                        println("Masz juz takiego shota w eq")
                                        break
                                    }
                                    else exists = false
                                }
                                if(!exists){
                                    println("Podniosles shota")
                                    eqVM.addItem(item)
                                }
                            }else{
                                println("Podniosles shota")
                                eqVM.addItem(item)
                            }
                        }
                    }
                }

                "115" ->
                {
                    cardVM.FlipFront();
                    Toast.makeText(context, "Mo??e juz pora wr??ci???", Toast.LENGTH_SHORT).show();

                    LocNav.SetCard(All.kolega, cardVM.card_front, cardVM.card_back_text, cardVM.card_back_title)
                    LocNav.AddChoice(cardVM.card_back_text, "Co? Juz masz dosc? Odprowadzi?? ci???",
                        "Mo??e Mi Si?? PoMoC PrZyDa??...",
                        "Nieeee, dam rad??. Bawcie sie dobrze.")

                    when (motionLayout.getCurrentState()) {
                        R.id.right -> {
                            checkpoint+="1"
                        }
                        R.id.left -> {
                            checkpoint+="0"
                        }
                    }
                }

                "1151"->{

                    GameOver("C????, dziesi???? szot??w to za ma??o. Prawie na " +
                            "trze??wo przywita??e?? ??????ko i mia??e?? ca??kowicie spokojny sen. Mo??e jutro powt??rka?",
                    motionLayout)


                }
                "1150"->
                {
                    //ci??g dalszy nast??pi
                }

                "GameOver"-> {

                    LocNav.AddChoice(
                        cardVM.card_back_text,
                        left = "Powt??rka?", right = "Mam do????...")

                    when (motionLayout.getCurrentState()) {

                        R.id.right -> {
                        //    System.exit(0); // :D
                        }
                        R.id.left -> {
                            StartAgain()
                        }

                    }
                }

            }
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    fun StartAgain()
    {
        checkpoint="0"
        Tutorial.shots=0
        cardVM.FlipFront()
        LocNav.SetLoc(
            All.samouczek,
            cardVM.card_front,
            cardVM.card_back_text,
            cardVM.card_back_title)
    }

    fun GameOver(reason: String, motionLayout: MotionLayout)
    {
        LocNav.SetCard(
            All.blankloc,
            cardVM.card_front,
            cardVM.card_back_text,
            cardVM.card_back_title
        )
        cardVM.card_back_title.text = "KONIEC GRY"

        LocNav.AddChoice(cardVM.card_back_text,reason,
            "Powt??rka?", "Mam do????...")

        when (motionLayout.getCurrentState()) {

            R.id.right -> {
                checkpoint="GameOver"
            }
            R.id.left -> {
                checkpoint="GameOver"
            }

        }

    }

    //Save game
    fun SaveGame(){
        var save = GameSave(0, checkpoint, LocNav.GetCurrentCard(cardVM.card_front).id)
        viewModelScope.launch(Dispatchers.IO){
            dao.InsertSave(save)
        }
    }

    fun GetAllSaves(){

    }
}