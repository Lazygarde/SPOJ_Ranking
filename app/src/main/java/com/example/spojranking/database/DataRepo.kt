package com.example.spojranking.database

import com.example.spojranking.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepo(private val Dao: DAO) {
    suspend fun getListData(): List<User> {
        return withContext(Dispatchers.IO) {
            Dao.getListData()
        }
    }

    suspend fun insert(user: User) {
        Dao.insert(user)
    }

    suspend fun update(user: User) {
        Dao.update(user)
    }
}