package com.example.karcianka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.karcianka.ViewModel.Adapter.EquipmentAdapter
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.EquipmentViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_main_game_equipment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_main_game_equipment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main_game_equipment, container, false)
    }

    private lateinit var eqVM: EquipmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryEquipmentViewModel = EquipmentViewModelFactory((requireNotNull(this.activity).application))
        eqVM = ViewModelProvider(requireActivity(), factoryEquipmentViewModel).get(
            EquipmentViewModel::class.java)

        val eqAdapter = EquipmentAdapter(eqVM.allItems, eqVM, this.requireContext())
        eqVM.allItems.observe(viewLifecycleOwner, {eqAdapter.notifyDataSetChanged()})

        var layoutManager = LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.equipmentView).let{
            it.adapter =eqAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<ImageButton>(R.id.mapButtonEq).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_equipment_to_fragment_main_game_map)
        }
        view.findViewById<ImageButton>(R.id.eqButtonEq).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_equipment_to_fragment_main_game)
        }
        view.findViewById<ImageButton>(R.id.menuButtonEq).setOnClickListener(){
            view.findNavController().navigate(R.id.action_fragment_main_game_equipment_to_fragment_main_game_menu)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_main_game_equipment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_main_game_equipment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}