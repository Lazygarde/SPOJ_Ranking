package com.example.spojranking.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.spojranking.data.User

@Dao
interface DAO {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("Select * from dataTable order by -solved")
    suspend fun getListData(): List<User>
}