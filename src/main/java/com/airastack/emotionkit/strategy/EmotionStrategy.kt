package com.airastack.emotionkit.strategy

import com.airastack.emotionkit.EmotionType

/**
 * 表情策略接口 - 定义所有表情策略的基本行为
 */
interface EmotionStrategy {
    fun getEmotionForState(state: EmotionState): EmotionType
    val strategyName: String
} 