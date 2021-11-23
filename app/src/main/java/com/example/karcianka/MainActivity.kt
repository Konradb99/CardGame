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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_fragment, Fragment_card(), "").commit()
        //Card swipe animations



        //Card flip animation


    }

}