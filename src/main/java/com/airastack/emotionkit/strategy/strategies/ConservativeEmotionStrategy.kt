package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * Conservative Emotion Strategy - tends to use neutral emotions, avoiding extreme expressions
 */
class ConservativeEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "Conservative Emotion Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // Even in special cases, maintain more conservative expressions
        
        // Maintenance mode is necessary
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // Other cases tend to use neutral or satisfied emotions
        return when {
            state.systemStatus == SystemStatusType.ERROR -> EmotionType.SUSPICIOUS  // Doesn't use PANIC
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.SATISFIED // Doesn't use HAPPY
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.SATISFIED
            state.taskSuccess == true -> EmotionType.SATISFIED
            state.taskSuccess == false -> EmotionType.NEUTRAL // Doesn't show failure too obviously
            state.environmentType == EnvironmentType.HAZARDOUS -> EmotionType.SUSPICIOUS // Doesn't use PANIC
            else -> EmotionType.NEUTRAL
        }
    }
} 