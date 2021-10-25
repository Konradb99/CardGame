package com.example.karcianka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.widget.TextView
import androidx.core.animation.doOnEnd
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var front_anim:AnimatorSet
    private lateinit var back_anim:AnimatorSet
    private var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Card swipe animations
        val SwipeVM = ViewModelProvider(this).get(SwipeRightViewModel::class.java)

        SwipeVM.modelStream.observe(this, { bindCard(it) })

        motionLayout.setTransitionListener(object: TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        SwipeVM.swipe()
                        //W tym miejscu wykrywa swipe
                        //Jak wykryc ktory swipe? xD

                        //Zmiana tla karty

                        //Zamienic na resource file?
                        var backgrounds = arrayOf(R.drawable.dragon, R.drawable.kapcio, R.drawable.losos, R.drawable.karta_morska, R.drawable.labirynt, R.drawable.mrowisko, R.drawable.narnia, R.drawable.witua)
                        var rand = Random   //Generowanie pseudolosowosci wyboru
                        var index:Int = rand.nextInt((backgrounds.size-1) - 0 + 1) + 0;
                        //Zmiana tla
                        card_front.setBackgroundResource(backgrounds.get(index))
                    }
                }
            }
        })


        //Card flip animation
        val scale = applicationContext.resources.displayMetrics.density

        val front = findViewById<TextView>(R.id.card_front) as TextView
        val back = findViewById<TextView>(R.id.card_back) as TextView

        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_card_flip) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_card_flip) as AnimatorSet

        //Back click -> jezeli klikniete w back card
        back.setOnClickListener{
            if(isFront)
            {
                front_anim.setTarget(front)
                back_anim.setTarget(back)
                front.isEnabled = false
                back.isEnabled = false
                front_anim.start()
                back_anim.start()
                back_anim.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                println("backclick")
                card_back.text="To jest opis Kapcia."
                isFront = false

            }
            else        // Akcja klikniecia dla backclick
            {
                front_anim.setTarget(back)
                back_anim.setTarget(front)
                front.isEnabled = false
                back.isEnabled = false
                back_anim.start()
                front_anim.start()
                back_anim.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                println("backclick else")
                isFront =true

            }
        }
    }

    private fun bindCard(model: SwipeRightModel) {
        topCard.setBackgroundColor(model.top.backgroundColor)
        bottomCard.setBackgroundColor(model.bottom.backgroundColor)
    }
}