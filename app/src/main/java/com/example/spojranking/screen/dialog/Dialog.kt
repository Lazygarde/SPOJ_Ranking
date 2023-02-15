package com.example.spojranking.screen.dialog


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.window.Dialog
import com.example.spojranking.data.User
import com.example.spojranking.screen.loadinganimation.ArcProgressbar
import com.example.spojranking.ui.theme.SPOJRankingTheme


@Composable
fun PopUpDialog(user: User, avt: Int, uiMode: Boolean, onDismiss: () -> Unit) {
    SPOJRankingTheme(darkTheme = uiMode) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            var score by remember {
                mutableStateOf(0)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 15.dp, start = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = avt),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)

                    )
                    Column {
                        Text(
                            text = user.name,
                            fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 10.dp, end = 15.dp)
                        )
                        Text(
                            text = user.userName,
                            fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(start = 10.dp, end = 15.dp)
                        )
                    }
                }
                ArcProgressbar(
                    solved = score.toFloat(),
                    target = user.target, uiMode
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier.padding(bottom = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1E88E5),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Close")
                    }
                }
            }
            score = user.solved
        }
    }

}