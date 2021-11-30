package com.example.taskerapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var description: String,
    var listColor: Int,
    var listName: String,
    var date: String,
    var time: String,
    var isHave: Boolean
) : Serializable