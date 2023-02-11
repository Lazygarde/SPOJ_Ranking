package com.example.spojranking.data

fun getName(name: String): String {
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