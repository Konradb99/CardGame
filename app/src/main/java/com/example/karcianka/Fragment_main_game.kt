package com.example.karcianka

import android.media.Image
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.karcianka.Model.LocNav
import com.example.karcianka.Model.SwipeRightModel
import com.example.karcianka.Model.Tutorial
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.GameViewModel
import com.example.karcianka.ViewModel.SwipeViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.CardViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.GameViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.SwipeViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_main_game.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_main_game : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main_game, container, false)
    }


    private lateinit var topCard: FrameLayout
    private lateinit var bottomCard: FrameLayout
    private lateinit var CardVM: CardViewModel
    private lateinit var SwipeVM: SwipeViewModel
    private var mLastClickTime = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("New view created")

        //card motion
        var motionLayout = view.findViewById<MotionLayout>(R.id.motionLayout)
        bottomCard = view.findViewById(R.id.bottomCard)
        topCard = view.findViewById(R.id.topCard)
        val factorySwipeVM = SwipeViewModelFactory((requireNotNull(this.activity).application))
        SwipeVM = ViewModelProvider(requireActivity(), factorySwipeVM).get(SwipeViewModel::class.java)
        val factoryCardVM = CardViewModelFactory((requireNotNull(this.activity).application), this.requireContext())
        CardVM = ViewModelProvider(requireActivity(), factoryCardVM).get(CardViewModel::class.java)

        var currentLoc = LocNav.GetCurrentLoc(CardVM.card_front)
        LocNav.SetCard(currentLoc, CardVM.card_front, CardVM.card_back_text, CardVM.card_back_title)


        if(CardVM.gameNotStarted == 1){
            println("First time adding card to game")
            CardVM.updateCard(view.findViewById<TextView>(R.id.card_front) as TextView, view.findViewById<LinearLayout>(R.id.card_back) as LinearLayout, view.findViewById<TextView>(R.id.card_back_text) as TextView, view.findViewById<TextView>(R.id.card_back_title) as TextView)
            CardVM.gameNotStarted = 0
        }
        else{
            println("Not updating view")
        }
        val factoryGameVM =
            GameViewModelFactory((requireNotNull(this.activity).application), CardVM, this.requireContext())
        val GameVM = ViewModelProvider(requireActivity(), factoryGameVM).get(GameViewModel::class.java)

        SwipeVM.modelStream.observe(viewLifecycleOwner, { bindCard(it) })
        CardVM.card_front.tag = R.drawable.biblioteka

        motionLayout.setTransitionListener(object: TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass,
                    R.id.offScreenLike -> {
                        //Motion
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.right)
                        SwipeVM.swipe()
                        //Detect swipe

                        GameVM.checkpoints(motionLayout, R.id.left, R.id.right)
                    }
                }
            }
        } )
        view.findViewById<LinearLayout>(R.id.card_back).setOnClickListener{
            //if(SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            //    return@setOnClickListener
            //}
            //mLastClickTime = SystemClock.elapsedRealtime()
            println("clicked card")
            CardVM.Flip()
        }

        //Ingame menu buttons
        view.findViewById<ImageButton>(R.id.menuButtonGame).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_to_fragment_main_game_menu)
        }
        view.findViewById<ImageButton>(R.id.eqButtonGame).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_to_fragment_main_game_equipment)
        }
        view.findViewById<ImageButton>(R.id.mapButtonGame).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_to_fragment_main_game_map)
        }

        //Enter button
        view.findViewById<ImageButton>(R.id.enter_btnGame).setOnClickListener{

            println("test")

            if(GameVM.checkpoint=="0")
            {
                Tutorial.EnterSolarisSamouczek(this.requireContext(), view.findViewById(R.id.card_front), view.findViewById(R.id.card_back), view.findViewById<TextView>(R.id.card_back_text) , view.findViewById<TextView>(R.id.card_back_title), CardVM, GameVM)

                CardVM.FlipFront_instant()
                GameVM.checkpoint+="1"
                view.findViewById<TextView>(R.id.card_back_text).text=
                    view.findViewById<TextView>(R.id.card_back_text).text.toString()+"\n\n"+GameVM.checkpoint;
            }
            if(GameVM.checkpoint=="0111") {

                Tutorial.EnterMinisterstwoSamouczek(view, view.findViewById<TextView>(R.id.card_front), view.findViewById<LinearLayout>(R.id.card_back), view.findViewById<TextView>(R.id.card_back_text) , view.findViewById<TextView>(R.id.card_back_title), CardVM, GameVM)
                GameVM.checkpoint="1"
                CardVM.FlipFront_instant()
                //view.findViewById<TextView>(R.id.card_back_text).text=
                //    view.findViewById<TextView>(R.id.card_back_text).text.toString()+"\n\n"+GameVM.checkpoint;
                //   view.findViewById<ImageButton>(R.id.enter_btn).setEnabled(false)
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
         * @return A new instance of fragment Fragment_main_game.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_main_game().apply {
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

}