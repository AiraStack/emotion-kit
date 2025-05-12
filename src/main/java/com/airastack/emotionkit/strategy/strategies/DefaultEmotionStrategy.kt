package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.TaskComplexity
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * Default Emotion Strategy - direct mapping based on current state
 */
class DefaultEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "Default Emotion Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // Priority processing
        
        // 1. If in maintenance mode
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // 2. If system status is error
        if (state.systemStatus == SystemStatusType.ERROR) {
            return EmotionType.PANIC
        }
        
        // 3. If battery level is low and not charging
        if (state.batteryLevel != null && state.batteryLevel < 15 && state.isCharging == false) {
            return EmotionType.SUSPICIOUS
        }
        
        // 4. Handle other normal states
        return when {
            // User interaction has priority
            state.userInteraction != null -> handleUserInteraction(state.userInteraction)
            
            // System status comes second
            state.systemStatus != null -> handleSystemStatus(state.systemStatus)
            
            // Task completion status
            state.taskSuccess != null -> handleTaskCompletion(state.taskSuccess, state.taskComplexity ?: TaskComplexity.MEDIUM)
            
            // Environment status
            state.environmentType != null -> handleEnvironment(state.environmentType)
            
            // Default case: keep current emotion
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