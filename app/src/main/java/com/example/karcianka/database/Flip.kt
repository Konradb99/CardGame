package com.example.karcianka.database

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.*
import com.example.karcianka.LocNav
import com.example.karcianka.R

class Flip {
    companion object {
        private lateinit var front_anim_instant: AnimatorSet
        private lateinit var back_anim_instant: AnimatorSet
        private lateinit var front_anim: AnimatorSet
        private lateinit var back_anim: AnimatorSet
        var isFront_instant: Boolean = true
        var isFront_text: Boolean = true
        var isFront_linear: Boolean = true
        //Instant flip
        fun  Animate_instant(appContext: Context?, front: TextView, back: LinearLayout){

            front_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip_instant) as AnimatorSet
            back_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip_instant) as AnimatorSet
            if(!isFront_instant)
            {
                front_anim_instant.setTarget(back)
                back_anim_instant.setTarget(front)
                front.isEnabled = false
                back.isEnabled = false
                back_anim_instant.start()
                front_anim_instant.start()
                back_anim_instant.doOnEnd {
                    back.isEnabled = true
                    front.isEnabled = true
                }
                isFront_instant =true
            }
        }


        //Normal flip
        fun  Animate(appContext: Context?, front: TextView, back: LinearLayout){
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
            if(isFront_text)
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
                isFront_text = false
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
                isFront_text =true
            }
        }
        fun  Animate(appContext: Context?, front: TextView, back: TextView){
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
            if(isFront_linear)
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
                isFront_linear = false

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
                isFront_linear =true
            }
        }
    }
}