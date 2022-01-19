package com.example.karcianka

import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.GameEntity.All
import com.example.karcianka.GameEntity.FlipModel
import com.example.karcianka.Model.LocNav
import com.example.karcianka.Model.Tutorial
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.GameViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.CardViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.GameViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_gme.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_game : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getParentFragmentManager().beginTransaction().add(R.id.main_view_game, Fragment_card(), "").commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fr = fragmentManager?.beginTransaction()
        fr?.add(R.id.menu_game, Fragment_game_menu())
        //fr?.add(R.id.main_view_game, Fragment_card())
        fr?.commit()

        val factoryCardVM =CardViewModelFactory((requireNotNull(this.activity).application), this.requireContext())
        val CardVM = ViewModelProvider(requireActivity(), factoryCardVM).get(CardViewModel::class.java)
        val factoryGameVM =
            GameViewModelFactory((requireNotNull(this.activity).application), CardVM, this.requireContext())
        val GameVM = ViewModelProvider(requireActivity(), factoryGameVM).get(GameViewModel::class.java)


        view.findViewById<ImageButton>(R.id.enter_btn).setOnClickListener{

            if(GameVM.checkpoint=="0")
            {
                Tutorial.EnterSolarisSamouczek(this.requireContext(), view.findViewById<TextView>(R.id.card_front), view.findViewById<LinearLayout>(R.id.card_back), view.findViewById<TextView>(R.id.card_back_text) , view.findViewById<TextView>(R.id.card_back_title), CardVM, GameVM)

                CardVM.FlipFront_instant()
                GameVM.checkpoint+="1"
                view.findViewById<TextView>(R.id.card_back_text).text=
                    view.findViewById<TextView>(R.id.card_back_text).text.toString()+"\n\n"+GameVM.checkpoint;
                //   view.findViewById<ImageButton>(R.id.enter_btn).setEnabled(false)
            }
            if(GameVM.checkpoint=="0111") {
                Tutorial.EnterMinisterstwoSamouczek(view, view.findViewById<TextView>(R.id.card_front), view.findViewById<LinearLayout>(R.id.card_back), view.findViewById<TextView>(R.id.card_back_text) , view.findViewById<TextView>(R.id.card_back_title), CardVM, GameVM)
                CardVM.FlipFront_instant()

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
         * @return A new instance of fragment Fragment_gme.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_game().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}