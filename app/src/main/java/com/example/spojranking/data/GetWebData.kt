package com.example.spojranking.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import java.lang.String
import kotlin.Int

class GetWebData {
    suspend fun get(): List<User> = withContext(Dispatchers.IO) {
        val users = getListOfUser()
        val deferredUsers = users.map { user ->
            async {
                try {
                    val url = "https://www.spoj.com/PTIT/users/${user.userName}/"
                    val doc = Jsoup.connect(url).get()
                    val table: Element? = doc.select("table.table-condensed").first()
                    var cnt = 0
                    if (table != null) {
                        val listData: Elements = table.select("td > a")
                        if (listData.size > 0) {
                            for (data in listData) {
                                if (data.attr("href")
                                        .contains(user.userName) && data.attr("href")
                                        .contains("/PTIT/status/")
                                ) {
                                    val index1 = String.valueOf(data).indexOf("/PTIT/status/")
                                    val index2: Int =
                                        String.valueOf(data).indexOf(user.userName)
                                    if (String.valueOf(data)
                                            .substring(index1, index2).length > 14
                                    ) {
                                        cnt++
                                    }
                                }
                            }
                        }
                    }
                    user.setSolve(cnt)
                    user
                } catch (e: IOException) {
                    user
                }
            }
        }
        val result = deferredUsers.awaitAll()
        result.sortedByDescending { it.solved }
    }
}