package com.example.karcianka.ViewModel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.Database.Entity.GameSave
import com.example.karcianka.Model.Game
import com.example.karcianka.R
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.GameViewModel
import org.w3c.dom.Text

class SavesAdapter(private val saves: LiveData<List<GameSave>>, private val viewModel: GameViewModel, context: Context): RecyclerView.Adapter<SavesAdapter.SavesHolder>() {

    private val context: Context? = context

    inner class SavesHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var saveNumber = view.findViewById<TextView>(R.id.saveNumber)
        var checkpointNumber = view.findViewById<TextView>(R.id.CheckpointNumber)
        var loadBtn = view.findViewById<ImageView>(R.id.loadSaveBtn)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavesAdapter.SavesHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_save_row, parent, false)
        return SavesHolder(view)
    }


    override fun onBindViewHolder(holder: SavesAdapter.SavesHolder, position: Int) {
        holder.saveNumber.text = "Zapis " + saves.value?.get(position)?.id.toString()
        holder.checkpointNumber.text = "Checkpoint " + saves.value?.get(position)?.checkpoint
        //Load selected save
        holder.loadBtn.setOnClickListener(){
            viewModel.checkpoint = saves.value?.get(position)?.checkpoint!!
            holder.myView.findNavController().navigate(R.id.action_fragment_game_saves_to_fragment_main_game)
        }
    }

    override fun getItemCount()=saves.value?.size?:0
}