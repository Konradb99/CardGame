package com.example.karcianka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd

class MainActivity : AppCompatActivity() {

    private lateinit var front_anim:AnimatorSet
    private lateinit var back_anim:AnimatorSet
    private var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Card swipe animations
        val viewModel = ViewModelProvider(this).get(SwipeRightViewModel::class.java)

        viewModel.modelStream.observe(this, Observer { bindCard(it) })

        motionLayout.setTransitionListener(object: TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        viewModel.swipe()
                    }
                }
            }
        })


        //Card flip animation
        var scale = applicationContext.resources.displayMetrics.density

        val front = findViewById<TextView>(R.id.card_front) as TextView
        val back =findViewById<TextView>(R.id.card_back) as TextView

        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_card_flip) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_card_flip) as AnimatorSet

        front.setOnClickListener{
            if(isFront)
            {
                front_anim.setTarget(front);
                back_anim.setTarget(back);
                back.isEnabled = false
                front.isEnabled = false
                front_anim.start()
                back_anim.start()
                back_anim.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                isFront = false
            }
            else
            {
                front_anim.setTarget(back)
                back_anim.setTarget(front)
                back.isEnabled = false
                front.isEnabled = false
                back_anim.start()
                front_anim.start()
                back_anim.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                isFront =true

            }
        }

        back.setOnClickListener{
            if(isFront)
            {
                front_anim.setTarget(front);
                back_anim.setTarget(back);
                front.isEnabled = false
                back.isEnabled = false
                front_anim.start()
                back_anim.start()
                back_anim.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                card_back.text="To jest opis Kapcia."
                isFront = false

            }
            else
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
                isFront =true

            }
        }
    }

    private fun bindCard(model: SwipeRightModel) {
        topCard.setBackgroundColor(model.top.backgroundColor)
        bottomCard.setBackgroundColor(model.bottom.backgroundColor)
    }
}