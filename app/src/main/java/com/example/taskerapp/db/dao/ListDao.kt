package com.example.taskerapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskerapp.db.model.ListModel

@Dao
interface ListDao {
    @Insert
    fun addList(listModel: ListModel)

    @Query("select * from listmodel")
    fun getAllList(): List<ListModel>

    @Update
    fun editList(listModel: ListModel)
}
