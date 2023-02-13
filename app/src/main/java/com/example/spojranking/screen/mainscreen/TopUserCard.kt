package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spojranking.data.User
import com.example.spojranking.screen.dialog.PopUpDialog


@Composable
fun TopUserCard(top: Int, high: Int, user: User, color: Color) {
    var showPopUp by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = user.image),
            contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable { showPopUp = true }
        )
        Text(
            text = user.getShortName(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = user.solved.toString(),
            fontSize = 14.sp,
            color = Color.White, modifier = Modifier.padding(bottom = 5.dp)
        )
        Box(
            modifier = Modifier
                .height(high.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                .background(color)
        ) {
            Text(
                text = top.toString(),
                fontSize = 35.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    if (showPopUp) {
        PopUpDialog(user) {
            showPopUp = false
        }
    }
}