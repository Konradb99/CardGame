package com.example.karcianka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.karcianka.database.Flip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_equipment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_equipment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_equipment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Specify cards of equipment
        var card1 = mutableListOf<TextView>()
        card1.add(view.findViewById<TextView>(R.id.eq_card_1_front) as TextView)
        card1.add(view.findViewById<TextView>(R.id.eq_card_1_back) as TextView)
        var card2 = mutableListOf<TextView>()
        card2.add(view.findViewById<TextView>(R.id.eq_card_2_front) as TextView)
        card2.add(view.findViewById<TextView>(R.id.eq_card_2_back) as TextView)
        var card3 = mutableListOf<TextView>()
        card3.add(view.findViewById<TextView>(R.id.eq_card_3_front) as TextView)
        card3.add(view.findViewById<TextView>(R.id.eq_card_3_back) as TextView)
        var card4 = mutableListOf<TextView>()
        card4.add(view.findViewById<TextView>(R.id.eq_card_4_front) as TextView)
        card4.add(view.findViewById<TextView>(R.id.eq_card_4_back) as TextView)

        //getActivity()?.getApplicationContext()

        //Handle onClick for all cards from eq

        view.findViewById<TextView>(R.id.eq_card_1_back).setOnClickListener(){
            Flip.Animate(getActivity()?.getApplicationContext(), card1.elementAt(0), card1.elementAt(1))
        }
        view.findViewById<TextView>(R.id.eq_card_2_back).setOnClickListener(){
            Flip.Animate(getActivity()?.getApplicationContext(), card2.elementAt(0), card2.elementAt(1))
        }
        view.findViewById<TextView>(R.id.eq_card_3_back).setOnClickListener(){
            Flip.Animate(getActivity()?.getApplicationContext(), card3.elementAt(0), card3.elementAt(1))
        }
        view.findViewById<TextView>(R.id.eq_card_4_back).setOnClickListener(){
            Flip.Animate(getActivity()?.getApplicationContext(), card4.elementAt(0), card4.elementAt(1))
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_equipment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_equipment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}