package com.airastack.emotionkit.strategy

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.TaskComplexity
import com.airastack.emotionkit.UserInteractionType

/**
 * Emotion state - contains all factors that may affect emotion selection
 */
data class EmotionState(
    val userInteraction: UserInteractionType? = null,
    val systemStatus: SystemStatusType? = null,
    val taskSuccess: Boolean? = null,
    val taskComplexity: TaskComplexity? = null,
    val environmentType: EnvironmentType? = null,
    val batteryLevel: Int? = null,
    val isCharging: Boolean? = null,
    val isInMaintenanceMode: Boolean = false,
    val currentEmotion: EmotionType = EmotionType.NEUTRAL,
    // Can be extended with other state factors as needed
) 