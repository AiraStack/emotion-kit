package com.airastack.emotionkit.rive

import android.content.Context
import com.airastack.emotionkit.EmotionType
import app.rive.runtime.kotlin.core.RiveFile

object RiveExpressionManager {
    private var riveFile: RiveFile? = null

    fun load(context: Context) {
        if (riveFile == null) {
            val input = context.resources.openRawResource(R.raw.robot_expressions)
            riveFile = RiveFile(input.readBytes())
            input.close()
        }
    }

    fun getArtboardName(emotionType: EmotionType): String = when (emotionType) {
        EmotionType.SUSPICIOUS -> "Suspicious"
        EmotionType.HAPPY -> "Happy"
        EmotionType.SATISFIED -> "Satisfied"
        EmotionType.NEUTRAL -> "Neutral"
        EmotionType.PANIC -> "Panic"
        EmotionType.REPAIR -> "Repair"
    }

    fun getStateMachineName(emotionType: EmotionType): String = getArtboardName(emotionType) // 可自定义
}