package com.example.taskerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskerapp.databinding.ItemTask2Binding
import com.example.taskerapp.db.model.TaskModel

class SecondTaskAdapter(var list: List<TaskModel>,var listener:OnItemClick):RecyclerView.Adapter<SecondTaskAdapter.MySecondTaskHolder>() {

    inner class MySecondTaskHolder(var itemTask2Binding: ItemTask2Binding) :
        RecyclerView.ViewHolder(itemTask2Binding.root) {
        fun onBind(taskModel: TaskModel) {
            itemTask2Binding.apply {
                nameTv.text = taskModel.description

            }
            itemView.setOnClickListener {
                listener.onItemClick(taskModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySecondTaskHolder {
        return MySecondTaskHolder(
            ItemTask2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MySecondTaskHolder, position: Int) {
        holder.onBind(list[position])


    }

    override fun getItemCount(): Int =list.size

    interface OnItemClick {
        fun onItemClick(taskModel: TaskModel)
    }
}