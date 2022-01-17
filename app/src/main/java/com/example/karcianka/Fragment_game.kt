package com.example.karcianka

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.animation.doOnEnd
import com.example.karcianka.GameEntity.Flip
import com.example.karcianka.Model.Tutorial

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    private lateinit var front_anim: AnimatorSet
    private lateinit var back_anim: AnimatorSet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fr = fragmentManager?.beginTransaction()
        fr?.add(R.id.menu_game, Fragment_game_menu())
        fr?.add(R.id.main_view_game, Fragment_card())
        fr?.commit()

        view.findViewById<ImageButton>(R.id.enter_btn).setOnClickListener{


            Tutorial.EnterSolarisSamouczek(this.requireContext(), view.findViewById<TextView>(R.id.card_front),
                view.findViewById<TextView>(R.id.card_back_text) , view.findViewById<TextView>(R.id.card_back_title))

            val front = view.findViewById<TextView>(R.id.card_front) as TextView
            val back = view.findViewById<LinearLayout>(R.id.card_back) as LinearLayout

            Flip.Animate_instant(getActivity()?.getApplicationContext(), front, back)


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