package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * Expressive emotion strategy - more varied and intense emotional responses
 */
class ExpressiveEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "Expressive Emotion Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // More expressive and intense emotional responses
        
        // Priority handling for specific cases
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        if (state.systemStatus == SystemStatusType.ERROR) {
            return EmotionType.PANIC
        }
        
        if (state.batteryLevel != null && state.batteryLevel < 20) {
            return if (state.isCharging == true) EmotionType.SATISFIED else EmotionType.PANIC
        }
        
        // General case handling with more expressive emotions
        return when {
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.QUESTION -> EmotionType.SUSPICIOUS // More curious
            state.userInteraction == UserInteractionType.COMPLEX_QUERY -> EmotionType.PANIC // More nervous
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.HAPPY // More happy
            
            state.taskSuccess == true -> EmotionType.HAPPY // Always very happy
            state.taskSuccess == false -> EmotionType.SUSPICIOUS // Always clearly dissatisfied
            
            state.environmentType == EnvironmentType.UNFAMILIAR -> EmotionType.PANIC // More afraid of unfamiliar environments
            state.environmentType == EnvironmentType.OPTIMAL -> EmotionType.HAPPY
            
            else -> state.currentEmotion
        }
    }
} 