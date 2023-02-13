package com.example.spojranking.data

data class User(
    val image: Int,
    val name: String,
    val userName: String,
    var solved: Int,
    val target: Int
){
    fun getShortName() : String{
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
    fun setSolve(solved: Int){
        this.solved = solved
    }
}
