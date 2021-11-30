package com.example.karcianka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_game_menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_game_menu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var opened_menu: Boolean = false
    private var opened_eq: Boolean = false

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
        return inflater.inflate(R.layout.fragment_game_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var menuButton = view.findViewById<ImageButton>(R.id.menuButton)

        menuButton.setOnClickListener(){
            if(!opened_menu){
                var fr = fragmentManager?.beginTransaction()
                fr?.replace(R.id.main_view_game, fragment_ingame_menu())
                fr?.commit()
                opened_menu = true
            }
            else if(opened_menu) {
                var fr = fragmentManager?.beginTransaction()
                fr?.replace(R.id.main_view_game, Fragment_card())
                fr?.commit()
                opened_menu = false
            }
        }

        var eqButton = view.findViewById<ImageButton>(R.id.eqButton)

        eqButton.setOnClickListener(){
            if(!opened_eq){
                var fr = fragmentManager?.beginTransaction()
                fr?.replace(R.id.main_view_game, Fragment_equipment())
                fr?.commit()
                opened_eq = true
            }
            else if(opened_eq){
                var fr = fragmentManager?.beginTransaction()
                fr?.replace(R.id.main_view_game, Fragment_card())
                fr?.commit()
                opened_eq = false
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
         * @return A new instance of fragment Fragment_game_menu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_game_menu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}