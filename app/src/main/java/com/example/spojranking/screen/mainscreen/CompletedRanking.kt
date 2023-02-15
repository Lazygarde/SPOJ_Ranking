package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.spojranking.data.User
import com.example.spojranking.data.getListAvt
import com.example.spojranking.ui.theme.SPOJRankingTheme

@Composable
fun CompletedRanking(list: List<User>, uiMode : Boolean, onClick: (User, Int) -> Unit) {
    val listAvt = getListAvt()
    SPOJRankingTheme(darkTheme = uiMode) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Divider(
                modifier = Modifier
                    .padding(start = 142.dp, end = 142.dp, bottom = 5.dp, top = 5.dp),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
            LazyColumn {
                items(list.size) {
                    UserCard(it + 1, list[it], listAvt[it],uiMode, onClick)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.width(58.dp))
                        Divider(color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }

        }
    }

}