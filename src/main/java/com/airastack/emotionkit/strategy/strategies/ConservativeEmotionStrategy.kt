package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * Conservative emotion strategy - tends to use neutral emotions, avoids extreme emotions
 */
class ConservativeEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "Conservative Emotion Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // Even in special cases, maintain more conservative emotions
        
        // Maintenance mode is mandatory
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // Other cases tend to use neutral or satisfied emotions
        return when {
            state.systemStatus == SystemStatusType.ERROR -> EmotionType.SUSPICIOUS  // Don't use PANIC
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.SATISFIED // Don't use HAPPY
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.SATISFIED
            state.taskSuccess == true -> EmotionType.SATISFIED
            state.taskSuccess == false -> EmotionType.NEUTRAL // Don't show failure too obviously
            state.environmentType == EnvironmentType.HAZARDOUS -> EmotionType.SUSPICIOUS // Don't use PANIC
            else -> EmotionType.NEUTRAL
        }
    }
} 