package com.example.karcianka.ViewModel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.R
import com.example.karcianka.ViewModel.EquipmentViewModel

class EquipmentAdapter(private val items: LiveData<List<EquipmentItems>>, private val viewModel: EquipmentViewModel, context: Context): RecyclerView.Adapter<EquipmentAdapter.EquipmentHolder>() {

    private val context: Context? = context

    inner class EquipmentHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val itemname = view.findViewById<TextView>(R.id.itemName)
        val cardBack = view.findViewById<TextView>(R.id.eq_card_1_back)
        var cardFront = view.findViewById<TextView>(R.id.eq_card_1_front)
        val myView = view
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.equipment_row, parent, false)
        return EquipmentHolder(view)
    }


    override fun onBindViewHolder(holder: EquipmentHolder, position: Int) {
        holder.itemname.text = items.value?.get(position)?.name
        holder.cardFront.setBackgroundResource(items.value?.get(position)?.background!!)
        holder.cardBack.setBackgroundResource(items.value?.get(position)?.backgroundBack!!)

    }

    override fun getItemCount()=items.value?.size?:0
}