package com.example.spojranking.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dataTable")
data class User(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "solved") var solved: Int,
    @ColumnInfo(name = "target") var target: Int
) {


    fun getShortName(): String {
        var p = name.length - 1
        var s = 0
        while (p >= 0) {
            if (name[p] == ' ') {
                s += 1
            }
            if (s == 2) {
                break
            }
            p -= 1
        }
        return name.substring(p + 1, name.length)
    }

    fun setSolve(solved: Int) {
        this.solved = solved
    }
}
