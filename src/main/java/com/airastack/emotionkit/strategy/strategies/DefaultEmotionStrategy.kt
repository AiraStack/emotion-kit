package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.TaskComplexity
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * 默认表情策略 - 基于当前状态的直接映射
 */
class DefaultEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "默认表情策略"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // 优先级处理
        
        // 1. 如果在维护模式
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // 2. 如果系统状态是错误状态
        if (state.systemStatus == SystemStatusType.ERROR) {
            return EmotionType.PANIC
        }
        
        // 3. 如果电池电量低且不在充电
        if (state.batteryLevel != null && state.batteryLevel < 15 && state.isCharging == false) {
            return EmotionType.SUSPICIOUS
        }
        
        // 4. 处理其他普通状态
        return when {
            // 用户交互优先
            state.userInteraction != null -> handleUserInteraction(state.userInteraction)
            
            // 系统状态次之
            state.systemStatus != null -> handleSystemStatus(state.systemStatus)
            
            // 任务完成状态
            state.taskSuccess != null -> handleTaskCompletion(state.taskSuccess, state.taskComplexity ?: TaskComplexity.MEDIUM)
            
            // 环境状态
            state.environmentType != null -> handleEnvironment(state.environmentType)
            
            // 默认情况保持当前表情
            else -> state.currentEmotion
        }
    }
    
    private fun handleUserInteraction(interactionType: UserInteractionType): EmotionType {
        return when (interactionType) {
            UserInteractionType.GREETING -> EmotionType.HAPPY
            UserInteractionType.QUESTION -> EmotionType.NEUTRAL
            UserInteractionType.COMPLEX_QUERY -> EmotionType.SUSPICIOUS
            UserInteractionType.APPRECIATION -> EmotionType.SATISFIED
        }
    }
    
    private fun handleSystemStatus(systemStatus: SystemStatusType): EmotionType {
        return when (systemStatus) {
            SystemStatusType.NORMAL -> EmotionType.NEUTRAL
            SystemStatusType.ERROR -> EmotionType.PANIC
            SystemStatusType.MAINTENANCE -> EmotionType.REPAIR
            SystemStatusType.LOW_BATTERY -> EmotionType.SUSPICIOUS
            SystemStatusType.CHARGING -> EmotionType.SATISFIED
        }
    }
    
    private fun handleTaskCompletion(success: Boolean, complexity: TaskComplexity): EmotionType {
        return when {
            success && complexity == TaskComplexity.HIGH -> EmotionType.HAPPY
            success -> EmotionType.SATISFIED
            !success && complexity == TaskComplexity.HIGH -> EmotionType.PANIC
            else -> EmotionType.SUSPICIOUS
        }
    }
    
    private fun handleEnvironment(environmentType: EnvironmentType): EmotionType {
        return when (environmentType) {
            EnvironmentType.NORMAL -> EmotionType.NEUTRAL
            EnvironmentType.HAZARDOUS -> EmotionType.PANIC
            EnvironmentType.UNFAMILIAR -> EmotionType.SUSPICIOUS
            EnvironmentType.OPTIMAL -> EmotionType.HAPPY
        }
    }
} 