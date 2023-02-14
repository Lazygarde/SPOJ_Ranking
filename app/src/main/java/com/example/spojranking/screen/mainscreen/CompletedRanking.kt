package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.spojranking.data.User
import com.example.spojranking.data.getListAvt

@Composable
fun CompletedRanking(list: List<User>, onClick: (User, Int) -> Unit) {
    val listAvt = getListAvt()
    Column {
        Divider(
            modifier = Modifier
                .padding(start = 138.dp, end = 138.dp, bottom = 5.dp, top = 5.dp),
            thickness = 3.dp,
            color = Color.Black
        )
        LazyColumn {
            items(list.size) {
                UserCard(it + 1, list[it], listAvt[it], onClick)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(58.dp))
                    Divider()
                }
            }
        }

    }
}