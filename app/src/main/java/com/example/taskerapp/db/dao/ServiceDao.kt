package com.example.taskerapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskerapp.db.model.ListModel
import com.example.taskerapp.db.model.TaskModel

@Dao
interface ServiceDao {
    @Insert
    fun addTask(taskModel: TaskModel)


    @Query("select * from taskmodel")
    fun getAllTask(): List<TaskModel>

    @Update
    fun editTask(taskModel: TaskModel)

    @Query("select * from taskmodel where listName=:name")
    fun getAllListName(name: String): List<TaskModel>



}