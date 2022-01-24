package com.example.karcianka.GameEntity

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.*
import com.example.karcianka.R

class FlipModel {
    companion object {
        private lateinit var front_anim_instant: AnimatorSet
        private lateinit var back_anim_instant: AnimatorSet
        private lateinit var front_anim: AnimatorSet
        private lateinit var back_anim: AnimatorSet
        var isFront : Boolean = true;
        //Instant flip



        //Normal flip
        fun Animate(appContext: Context?, front: TextView, back: LinearLayout){
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
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
                isFront =true
            }
        }




        fun AnimateFront(appContext: Context?, front: TextView, back: LinearLayout){
            println(isFront)
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
            if(!isFront)
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



        fun AnimateBack(appContext: Context?, front: TextView, back: LinearLayout){
            println(isFront)
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
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
                isFront = false
            }
        }

        fun  AnimateFront_instant(appContext: Context?, front: TextView, back: LinearLayout){

            front_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip_instant) as AnimatorSet
            back_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip_instant) as AnimatorSet
            if(!isFront)
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
                isFront =true
            }
        }

        fun AnimateBack_instant(appContext: Context?, front: TextView, back: LinearLayout)
        {
            front_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip_instant) as AnimatorSet
            back_anim_instant = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip_instant) as AnimatorSet
            if(isFront)
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
                isFront = false;
            }
        }

        fun  Animate(appContext: Context?, front: TextView, back: TextView){
            val scale = appContext?.resources?.displayMetrics?.density
            front_anim = AnimatorInflater.loadAnimator(appContext, R.animator.front_card_flip) as AnimatorSet
            back_anim = AnimatorInflater.loadAnimator(appContext, R.animator.back_card_flip) as AnimatorSet
            front.cameraDistance = 8000 * scale!!
            back.cameraDistance = 8000 * scale
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
                isFront =true
            }
        }
    }
}