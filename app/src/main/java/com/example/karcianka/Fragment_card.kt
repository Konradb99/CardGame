package com.example.karcianka
import com.example.karcianka.GameEntity.Location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.Model.LocNav.Companion.GetNextLoc
import com.example.karcianka.Model.LocNav.Companion.SetLoc
import com.example.karcianka.GameEntity.All.Companion.blankloc
import com.example.karcianka.GameEntity.Flip
import com.example.karcianka.Model.Game
import com.example.karcianka.Model.SwipeRightModel
import com.example.karcianka.ViewModel.SwipeViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.SwipeViewModelFactory

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
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    private lateinit var game: Game
    private lateinit var topCard: FrameLayout
    private lateinit var bottomCard: FrameLayout
    private var isFront = true
    private var checkpoint = 0
    private var numberOfSwipes = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val front = view.findViewById<TextView>(R.id.card_front) as TextView
        val back = view.findViewById<LinearLayout>(R.id.card_back) as LinearLayout
        var motionLayout = view.findViewById<MotionLayout>(R.id.motionLayout)
        bottomCard = view.findViewById(R.id.bottomCard)
        topCard = view.findViewById(R.id.topCard)


        val factorySwipeVM =SwipeViewModelFactory((requireNotNull(this.activity).application))
        val SwipeVM = ViewModelProvider(requireActivity(), factorySwipeVM).get(SwipeViewModel::class.java)
        SwipeVM.modelStream.observe(viewLifecycleOwner, { bindCard(it) })
        front.setTag(R.drawable.biblioteka)
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
                        when(motionLayout.getCurrentState()){
                            R.id.pass -> Toast.makeText(getActivity(),"W Lewo!",Toast.LENGTH_SHORT).show();
                            R.id.like -> Toast.makeText(getActivity(),"W Prawo!",Toast.LENGTH_SHORT).show();
                        }
                        val card_back_text = view.findViewById<TextView>(R.id.card_back_text) as TextView
                        val card_back_title = view.findViewById<TextView>(R.id.card_back_title) as TextView

                        when(checkpoint)
                        {
                            -1 ->
                            {
                                //Move to model?
                                //var next_loc: Location = GetNextLoc(front)
                                //SetLoc(front, card_back_text, card_back_title, next_loc)
                                //numberOfSwipes+=1
                                //front.setBackgroundResource(blankloc.draw)
                                //Flip.Animate_instant(getActivity()?.getApplicationContext(), front, back)
                            }

                            0-> {
                                var next_loc: Location = GetNextLoc(front)
                                SetLoc(front, card_back_text, card_back_title, next_loc)
                                Flip.Animate_instant(getActivity()?.getApplicationContext(), front, back)
                                //numberOfSwipes+=1
                                //if(numberOfSwipes%10==0) checkpoint=1

                            }
                            1 ->{
                                var next_loc: Location = GetNextLoc(front)
                                SetLoc(front, card_back_text, card_back_title, next_loc)
                                Flip.Animate_instant(getActivity()?.getApplicationContext(), front, back)
                                checkpoint=0
                                Toast.makeText(getActivity(),"To juz dziesiaty swipe!",Toast.LENGTH_SHORT).show(); }
                        }
                        //labirynt to wyjatek -> prowadzi do wituly
                        //if (card_front.background.constantState == getResources().getDrawable(R.drawable.labirynt).getConstantState())
                        //{
                        //    //witua
                        //    card_front.setTag(All.witula.draw)
                        //    card_front.setBackgroundResource(All.witula.draw)
                        //}

                        //Save current card state



                    }
                }
            }
        } )
        back.setOnClickListener{
            Flip.Animate(getActivity()?.getApplicationContext(), front, back)
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