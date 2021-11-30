package com.example.taskerapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskerapp.R
import com.example.taskerapp.databinding.ItemTaskBinding
import com.example.taskerapp.db.AppDatabase
import com.example.taskerapp.db.model.TaskModel

class FirstTaskAdapter(var list: List<TaskModel>, var context: Context) :
    RecyclerView.Adapter<FirstTaskAdapter.MyFirstTaskHolder>() {
    private var appDatabase: AppDatabase = AppDatabase.getInstance(context)

    inner class MyFirstTaskHolder(var itemTaskBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(itemTaskBinding.root) {
        fun onBind(taskModel: TaskModel) {
            itemTaskBinding.apply {
                if (taskModel.isHave) {
                    imageCheck.setImageResource(R.drawable.ic_marked)
                }else{
                    imageCheck.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                }
                tvName.apply {
                    isSingleLine = true
                    isSelected = true
                }
                imageCheck.setOnClickListener {
                    if (taskModel.isHave) {
                        taskModel.isHave = false
                        imageCheck.setImageResource(R.drawable.ic_marked)
                        appDatabase.serviceDao().editTask(taskModel)
                    } else {
                        taskModel.isHave = true
                        appDatabase.serviceDao().editTask(taskModel)
                        imageCheck.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                    }
                }

                calendar.text = taskModel.date
                time.text = taskModel.time
                tvName.text = taskModel.description
                imageColor.setBackgroundColor(taskModel.listColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFirstTaskHolder {
        return MyFirstTaskHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyFirstTaskHolder, position: Int) {
        holder.onBind(list[position])

    }
    override fun getItemCount(): Int = list.size

}