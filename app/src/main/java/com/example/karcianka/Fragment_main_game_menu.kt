package com.example.karcianka

import android.app.FragmentManager
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.GameViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.CardViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.EquipmentViewModelFactory
import com.example.karcianka.ViewModel.ViewModeLFactory.GameViewModelFactory
import kotlin.system.exitProcess

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_main_game_menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_main_game_menu : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main_game_menu, container, false)
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


        view.findViewById<ImageButton>(R.id.menuButtonMenu).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_menu_to_fragment_main_game)
        }
        view.findViewById<ImageButton>(R.id.mapButtonMenu).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_menu_to_fragment_main_game_map)
        }
        view.findViewById<ImageButton>(R.id.eqButtonMenu).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_menu_to_fragment_main_game_equipment)
        }


        //Menu btns
        //Restart
        view.findViewById<ImageButton>(R.id.restart_btnMenu).setOnClickListener(){
            GameVM.StartAgain()
            view.findNavController().navigate(R.id.action_fragment_main_game_menu_to_fragment_main_game)
        }
        //Save game
        view.findViewById<ImageButton>(R.id.save_btnMenu).setOnClickListener(){

        }
        //Load save
        view.findViewById<ImageButton>(R.id.load_btnMenu).setOnClickListener(){

        }
        //Exit
        view.findViewById<ImageButton>(R.id.exit_btnMenu).setOnClickListener(){
            System.exit(0)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_main_game_menu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_main_game_menu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}