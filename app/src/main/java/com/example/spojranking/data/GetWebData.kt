package com.example.spojranking.data

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import java.lang.String
import kotlin.Int


class GetWebData {
    fun get(): List<User> { //kiểu gì cũng phải load hết dữ liệu thì mới có danh sách theo top được

        val list = mutableListOf<User>()
        val users = getListOfUser()
        val src = "https://www.spoj.com/PTIT/users/"
        for (i in 0 until users.size) {
            try {
                val url = src + users[i].userName + "/"
                var cnt = 0
                val doc = Jsoup.connect(url).get()
                val table: Element? = doc.select("table.table-condensed").first()
                if (table != null) {
                    val listData: Elements = table.select("td > a")
                    if (listData.size > 0) {
                        for (data in listData) {
                            if (data.attr("href")
                                    .contains(users[i].userName) && data.attr("href")
                                    .contains("/PTIT/status/")
                            ) {
                                val index1 = String.valueOf(data).indexOf("/PTIT/status/")
                                val index2: Int =
                                    String.valueOf(data).indexOf(users[i].userName)
                                if (String.valueOf(data)
                                        .substring(index1, index2).length > 14
                                ) {
                                    cnt++
                                }
                            }
                        }
                        users[i].setSolve(cnt)
                        list.add(users[i])
                    }
                }

            } catch (e: IOException) {
                continue
            }
        }
        list.sortBy { -it.solved }
        return list
    }

}