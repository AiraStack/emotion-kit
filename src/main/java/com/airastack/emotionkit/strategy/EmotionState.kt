package com.airastack.emotionkit.strategy

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.TaskComplexity
import com.airastack.emotionkit.UserInteractionType

/**
 * 表情状态 - 包含可能影响表情选择的所有因素
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
    // 可以根据需要扩展其他状态因素
) 