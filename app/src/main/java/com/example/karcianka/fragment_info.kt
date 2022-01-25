package com.example.karcianka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.GameViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.CardViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.EquipmentViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.GameViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_info : Fragment() {
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
        return inflater.inflate(R.layout.fragment_info, container, false)

    }

    private lateinit var CardVM: CardViewModel
    private lateinit var EqVM: EquipmentViewModel
    private lateinit var GameVM: GameViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryCardVM = CardViewModelFactory((requireNotNull(this.activity).application), this.requireContext())
        CardVM = ViewModelProvider(requireActivity(), factoryCardVM).get(CardViewModel::class.java)
        val factoryEqVM = EquipmentViewModelFactory((requireNotNull(this.activity).application))
        EqVM = ViewModelProvider(requireActivity(), factoryEqVM).get(EquipmentViewModel::class.java)
        val factoryGameVM = GameViewModelFactory((requireNotNull(this.activity).application), CardVM, EqVM, this.requireContext())
        GameVM = ViewModelProvider(requireActivity(), factoryGameVM).get(GameViewModel::class.java)


        view.findViewById<ImageButton>(R.id.backInfoBtn).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_info_to_fragment_menu)
        }



        view.findViewById<Button>(R.id.cheatPanelBtn).setOnClickListener(){
            var cheat = view.findViewById<EditText>(R.id.cheatPanelText).text.toString()
            when(cheat){
                "chck0000" -> GameVM.checkpoint = "0"
                "chck0001" -> GameVM.checkpoint = "1"
                "chck0115" -> GameVM.checkpoint = "115"
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
         * @return A new instance of fragment fragment_info.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_info().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}