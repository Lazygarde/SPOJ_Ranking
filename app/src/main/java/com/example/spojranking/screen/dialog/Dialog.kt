package com.example.spojranking.screen.dialog


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spojranking.data.User
import com.example.spojranking.screen.loadinganimation.ArcProgressbar


@Composable
fun PopUpDialog(user: User, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        UserProfile(user)
    }
}

@Composable
fun UserProfile(user: User) {
    var score by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
            .padding(7.dp)
            .clip(RoundedCornerShape(27.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = user.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)

            )
        }
        Text(
            text = user.name,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 35.dp)
        )
        Text(
            text = user.userName,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 35.dp, top = 8.dp, bottom = 5.dp)
        )
        ArcProgressbar(
            solved = score.toFloat(), target = user.target,
        )
    }
    score = user.solved
}
