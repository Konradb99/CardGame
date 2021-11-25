package com.example.karcianka

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_card.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_card.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_card : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        //Swipe card



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    private lateinit var front_anim: AnimatorSet
    private lateinit var back_anim: AnimatorSet
    private var isFront = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val SwipeVM = ViewModelProvider(this).get(SwipeRightViewModel::class.java)

        SwipeVM.modelStream.observe(viewLifecycleOwner, { bindCard(it) })

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
                        var backgrounds = arrayOf(
                            R.drawable.dragon,
                            R.drawable.kapcio,
                            R.drawable.losos,
                            R.drawable.karta_morska,
                            R.drawable.labirynt,
                            R.drawable.mrowisko,
                            R.drawable.narnia,
                            R.drawable.witua,
                            R.drawable.firewall,
                            R.drawable.kapciomag,
                            R.drawable.kapciomagrozmazany,
                            R.drawable.utm
                        )
                        var rand = Random   //Generowanie pseudolosowosci wyboru
                        var index: Int = rand.nextInt((backgrounds.size - 1) - 0 + 1) + 0;
                        //Zmiana tla
                        card_front.setBackgroundResource(backgrounds.get(index))
                    }
                }
            }
        } )



        //Flip card
        val scale = getActivity()?.getApplicationContext()?.resources?.displayMetrics?.density

        val front = view.findViewById<TextView>(R.id.card_front) as TextView
        val back = view.findViewById<TextView>(R.id.card_back) as TextView

        front.cameraDistance = 8000 * scale!!
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(getActivity()?.getApplicationContext(), R.animator.front_card_flip) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(getActivity()?.getApplicationContext(), R.animator.back_card_flip) as AnimatorSet

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_card.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_card().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun bindCard(model: SwipeRightModel) {
        topCard.setBackgroundColor(model.top.backgroundColor)
        bottomCard.setBackgroundColor(model.bottom.backgroundColor)
    }
    //Swipe card
}