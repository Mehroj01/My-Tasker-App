package com.example.taskerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskerapp.databinding.ItemColorBinding

class ColorAdapter(var list: List<Int>, var listener: OnItemClickListener) :
    RecyclerView.Adapter<ColorAdapter.MyColorHolder>() {
    inner class MyColorHolder(var itemColorBinding: ItemColorBinding) :
        RecyclerView.ViewHolder(itemColorBinding.root) {
        fun onBind(color: Int) {
            itemView.setOnClickListener {
                listener.onItemClick(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyColorHolder {
        return MyColorHolder(
            ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyColorHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemColorBinding.image.setBackgroundColor(list[position])
    }

    override fun getItemCount(): Int = list.size
    interface OnItemClickListener {
        fun onItemClick(color: Int)

    }
}