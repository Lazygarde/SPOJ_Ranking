package com.example.spojranking.screen.loadinganimation


import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs

var maxProgressPerLevel: Int = 100
const val progressLimit = 300f
fun calculate(
    score: Float,
    level: Int,
): Float {
    return (abs(score - (maxProgressPerLevel * level)) / maxProgressPerLevel) * progressLimit
}

@Composable
fun ArcProgressbar(
    modifier: Modifier = Modifier,
    solved: Float, target: Int
) {

    maxProgressPerLevel = target
    var level by remember {
        mutableStateOf(solved / maxProgressPerLevel)
    }

    val targetAnimatedValue = calculate(solved, level.toInt())
    val progressAnimate =
        remember { androidx.compose.animation.core.Animatable(targetAnimatedValue) }
    val scoreAnimate = remember { androidx.compose.animation.core.Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(level, solved) {
        if (solved > 0f) {

            // animate progress
            coroutineScope.launch {
                progressAnimate.animateTo(
                    targetValue = targetAnimatedValue,
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                ) {
                    if (value >= progressLimit) {

                        coroutineScope.launch {
                            level++
                            progressAnimate.snapTo(0f)
                        }
                    }
                }
            }

            coroutineScope.launch {

                if (scoreAnimate.value > solved) {
                    scoreAnimate.snapTo(0f)
                }

                scoreAnimate.animateTo(
                    targetValue = solved,
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                )
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            PointsProgress(
                progress = {
                    progressAnimate.value
                }
            )
            CollectorLevel(solved, target)
        }

    }
}


@Composable
fun CollectorLevel(
    solved: Float, target: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 77.dp, bottom = 25.dp),
                text = "${solved.toInt()}/${target}",
                color = Color.Black,
                fontSize = 22.sp
            )
        }
        Text(
            text = "Process: ${(solved/target * 100).toInt()}%",
            color = Color.Black,
            fontSize = 20.sp, modifier = Modifier.padding(top = 30.dp)
        )
    }
}

@Composable
fun BoxScope.PointsProgress(
    progress: () -> Float
) {

    val start = 120f
    val end = 300f
    val thickness = 13.dp

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .padding(10.dp)
            .aspectRatio(1f)
            .align(Alignment.Center),
        onDraw = {
            // Background Arc
            drawArc(
                color = Color.LightGray,
                startAngle = start,
                sweepAngle = end,
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Square),
                size = Size(size.width, size.height)
            )

            // Foreground Arc
            drawArc(
                color = Color(0xFF3db39f),
                startAngle = start,
                sweepAngle = progress(),
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Square),
                size = Size(size.width, size.height)
            )
        }
    )
}