package com.example.spojranking.screen.mainscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spojranking.R
import com.example.spojranking.data.User
import com.example.spojranking.screen.dialog.PopUpDialog
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    var user: User by remember { mutableStateOf(User(0, "", "", 0, 0)) }
    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var showPopUp by remember { mutableStateOf(false) }
    val sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
    Box {
        ModalBottomSheetLayout(
            sheetShape = sheetShape,
            sheetState = sheetState,
            sheetContent = {
                CompletedRanking {
                    user = it
                    showPopUp = true
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.background))
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 40.dp),
                        text = "SPOJ Ranking",
                        fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )
                    Top()
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide()
                            else sheetState.show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier.padding(30.dp)
                ) {
                    Text(text = "Completed Ranking", color = Color.Black)
                }
            }
        }

    }
    if (showPopUp) {
        PopUpDialog(user) {
            showPopUp = false
        }
    }
}

