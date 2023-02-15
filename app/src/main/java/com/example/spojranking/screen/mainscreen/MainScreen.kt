package com.example.spojranking.screen.mainscreen


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spojranking.R
import com.example.spojranking.data.User
import com.example.spojranking.data.ViModel
import com.example.spojranking.data.ViewModelFactory
import com.example.spojranking.screen.dialog.PopUpDialog
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var user: User by remember { mutableStateOf(User(100, "", "", 0, 0)) }
    var avt: Int by remember { mutableStateOf(R.drawable._7) }

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var showPopUp by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val viModel: ViModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as Application)
    )
    val users = viModel.users.collectAsState()
    val loading = viModel.loadingState.collectAsState()

    Box {
        ModalBottomSheetLayout(
            sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
            sheetState = sheetState,
            sheetContent = {
                CompletedRanking(users.value) { userIt, avtIt ->
                    user = userIt
                    avt = avtIt
                    showPopUp = true
                }
            },
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .background(color = colorResource(id = R.color.background))
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier.padding(top = 15.dp, end = 15.dp)) {
                    Spacer(
                        modifier = modifier
                            .weight(1f)
                            .height(30.dp)
                    )
                    if (loading.value) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = modifier.size(30.dp)
                        )
                    }
                }
                //
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(460.dp)
                ) {
                    Text(
                        modifier = modifier.padding(top = 20.dp),
                        text = "SPOJ Ranking",
                        fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.White
                    )

                    Top(users.value)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide()
                            else sheetState.show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = modifier.padding(30.dp)
                ) {
                    Text(text = "Completed Ranking", color = Color.Black)
                }
            }
        }

    }
    if (showPopUp) {
        PopUpDialog(user, avt) {
            showPopUp = false
        }
    }
}
