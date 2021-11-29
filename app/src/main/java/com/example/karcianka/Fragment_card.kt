package com.example.karcianka
import com.example.karcianka.database.All
import com.example.karcianka.database.Location

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.LocNav.Companion.GetCurrentLoc
import com.example.karcianka.LocNav.Companion.GetNextLoc
import com.example.karcianka.LocNav.Companion.SetLoc
import com.example.karcianka.database.All.Companion.blankloc
import org.w3c.dom.Text
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
    private lateinit var game: Game
    private lateinit var topCard: FrameLayout
    private lateinit var bottomCard: FrameLayout
    private var isFront = true
    private var checkpoint = 0
    private var numberOfSwipes = 0
    private var current_location = Location()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var card_front = view.findViewById<TextView>(R.id.card_front)
        var card_back = view.findViewById<TextView>(R.id.card_back)
        var motionLayout = view.findViewById<MotionLayout>(R.id.motionLayout)
        bottomCard = view.findViewById(R.id.bottomCard)
        topCard = view.findViewById(R.id.topCard)

        val SwipeVM = ViewModelProvider(this).get(SwipeRightViewModel::class.java)
        SwipeVM.modelStream.observe(viewLifecycleOwner, { bindCard(it) })
        card_front.setTag(R.drawable.biblioteka)
        game = Game()
        checkpoint = game.checkpoints

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

                        val front = view.findViewById<TextView>(R.id.card_front) as TextView
                        val back = view.findViewById<TextView>(R.id.card_back) as TextView

                        when(checkpoint)
                        {
                            -1 ->
                            {
                                var next_loc: Location = GetNextLoc(card_front)
                                SetLoc(card_front, card_back, next_loc)
                                numberOfSwipes+=1
                                card_front.setBackgroundResource(blankloc.draw)
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
                                    println("backclick else")
                                    isFront =true

                                }

                            }

                            0-> {
                                var next_loc: Location = GetNextLoc(card_front)
                                SetLoc(card_front, card_back, next_loc)
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
                                    println("backclick else")
                                    isFront =true

                                }
                                numberOfSwipes+=1
                                if(numberOfSwipes%10==0) checkpoint=1

                            }
                            1 ->{
                                var next_loc: Location = GetNextLoc(card_front)
                                SetLoc(card_front, card_back, next_loc)
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
                                    println("backclick else")
                                    isFront =true

                                }
                                checkpoint=0
                                Toast.makeText(getActivity(),"To juz dziesiaty swipe!",Toast.LENGTH_SHORT).show(); }
                        }

                        //var next_loc: Location = GetNextLoc(card_front)

                        //card_front.setTag(next_loc.draw)
                        //card_front.setBackgroundResource(next_loc.draw)



                        //labirynt to wyjatek -> prowadzi do wituly
                        //if (card_front.background.constantState == getResources().getDrawable(R.drawable.labirynt).getConstantState())
                        //{
                        //    //witua
                        //    card_front.setTag(All.witula.draw)
                        //    card_front.setBackgroundResource(All.witula.draw)
                        //}
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
                current_location = GetCurrentLoc(card_front)
                card_back.text = current_location.description
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