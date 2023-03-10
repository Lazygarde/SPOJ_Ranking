package com.example.spojranking.screen.loadinganimation


import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spojranking.ui.theme.SPOJRankingTheme
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.min

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
    solved: Float, target: Int, uiMode: Boolean,
    modifier: Modifier = Modifier,
) {
    val maxSolvedValid = min(solved, target.toFloat())
    maxProgressPerLevel = target
    val level by remember {
        mutableStateOf(maxSolvedValid / maxProgressPerLevel)
    }

    val targetAnimatedValue = calculate(maxSolvedValid, level.toInt())
    val progressAnimate =
        remember { androidx.compose.animation.core.Animatable(targetAnimatedValue) }
    val scoreAnimate = remember { androidx.compose.animation.core.Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    SPOJRankingTheme (darkTheme = uiMode){
        LaunchedEffect(level, maxSolvedValid) {
            if (maxSolvedValid > 0f) {

                // animate progress
                coroutineScope.launch {
                    progressAnimate.animateTo(
                        targetValue = targetAnimatedValue,
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    ) {
                    }
                }

                coroutineScope.launch {

                    if (scoreAnimate.value > maxSolvedValid) {
                        scoreAnimate.snapTo(0f)
                    }

                    scoreAnimate.animateTo(
                        targetValue = maxSolvedValid,
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
                CollectorLevel(solved, target, MaterialTheme.colorScheme.secondary)
            }

        }
    }

}


@Composable
fun CollectorLevel(
    solved: Float, target: Int, color: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 80.dp, bottom = 55.dp),
                text = "${solved.toInt()}/${target}",
                color = color,
                fontSize = 20.sp
            )
        }
        Text(
            text = "Progress: ${(solved / target * 100).toInt()}%",
            color = color,
            fontSize = 18.sp
        )
    }
}

@Composable
fun BoxScope.PointsProgress(
    progress: () -> Float
) {

    val start = 120f
    val end = 300f
    val thickness = 14.dp

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