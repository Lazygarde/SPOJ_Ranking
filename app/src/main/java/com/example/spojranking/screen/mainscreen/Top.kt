package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.spojranking.R
import com.example.spojranking.data.User

@Composable
fun Top(list :List<User>, uiMode : Boolean){
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    2, 135, list[1],
                    colorResource(id = R.color.top2),
                    R.drawable._6, uiMode
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    1, 180, list[0],
                    colorResource(id = R.color.top1),
                    R.drawable._7, uiMode
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    3, 95, list[2],
                    colorResource(id = R.color.top3),
                    R.drawable._5, uiMode
                )
            }

        }
    }
}