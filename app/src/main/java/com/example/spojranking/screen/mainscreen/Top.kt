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
import com.example.spojranking.data.getListOfUser

@Composable
fun Top() {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            val list = getListOfUser()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    2, 120, list[1],
                    colorResource(id = R.color.top2)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    1, 150, list[0],
                    colorResource(id = R.color.top1)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                TopUserCard(
                    3, 80, list[2],
                    colorResource(id = R.color.top3)
                )
            }

        }
    }
}