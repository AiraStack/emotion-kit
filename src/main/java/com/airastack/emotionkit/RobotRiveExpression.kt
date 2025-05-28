package com.airastack.emotionkit.rive

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.rive.runtime.kotlin.compose.RiveAnimation
import com.airastack.emotionkit.EmotionType

@Composable
fun RobotRiveExpression(
    emotionType: EmotionType,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // 确保 Rive 文件只加载一次
    LaunchedEffect(Unit) {
        RiveExpressionManager.load(context)
    }

    val artboard = RiveExpressionManager.getArtboardName(emotionType)
    val stateMachine = RiveExpressionManager.getStateMachineName(emotionType)

    RiveAnimation(
        resId = com.airastack.emotionkit.R.raw.robot_expressions,
        artboardName = artboard,
        stateMachineName = stateMachine,
        modifier = modifier
    )
}