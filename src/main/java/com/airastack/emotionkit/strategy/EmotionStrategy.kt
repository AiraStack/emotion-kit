package com.airastack.emotionkit.strategy

import com.airastack.emotionkit.EmotionType

/**
 * Emotion strategy interface - defines the basic behavior of all emotion strategies
 */
interface EmotionStrategy {
    fun getEmotionForState(state: EmotionState): EmotionType
    val strategyName: String
} 