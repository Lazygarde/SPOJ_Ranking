package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spojranking.data.User
import com.example.spojranking.ui.theme.SPOJRankingTheme

@Composable
fun UserCard(top: Int, user: User, avt: Int, uiMode : Boolean,  onClick: (User, Int) -> Unit) {
    SPOJRankingTheme(darkTheme = uiMode) {
        Column(modifier = Modifier.clickable {
            onClick(user, avt)
        }) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.size(56.dp)
                ) {
                    Text(
                        text = top.toString(), color = MaterialTheme.colorScheme.secondary,
                        fontSize = 15.sp, modifier = Modifier.padding(start = 20.dp)
                    )
                }
                Box(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, end = 10.dp)) {
                    Image(
                        painter = painterResource(id = avt),
                        contentDescription = null, contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
                Text(text = user.name, color = MaterialTheme.colorScheme.secondary, fontSize = 15.sp)
                Spacer(Modifier.weight(1f))
                Text(
                    text = user.solved.toString(),
                    fontSize = 15.sp, color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(end = 24.dp)
                )
            }
        }
    }

}