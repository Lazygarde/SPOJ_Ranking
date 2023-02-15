package com.example.spojranking


import android.os.Bundle
import android.os.SystemClock.uptimeMillis
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import com.example.spojranking.screen.mainscreen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}


@Composable
fun Mainmmm() {
    var lastTapTime by remember { mutableStateOf(0L) }
    val doubleTap = remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) { doubleTap.value = true
            }
    ) {
        if (doubleTap.value) {
            // Xử lý sự kiện tap liên tiếp hai lần ở đây
            doubleTap.value = false
        }
    }



}
