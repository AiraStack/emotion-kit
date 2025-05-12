package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * 情绪化表情策略 - 表情变化更丰富，反应更强烈
 */
class ExpressiveEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "情绪化表情策略"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // 情绪表现更加丰富和强烈
        
        // 优先级处理特定情况
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        if (state.systemStatus == SystemStatusType.ERROR) {
            return EmotionType.PANIC
        }
        
        if (state.batteryLevel != null && state.batteryLevel < 20) {
            return if (state.isCharging == true) EmotionType.SATISFIED else EmotionType.PANIC
        }
        
        // 一般情况处理，表情更加情绪化
        return when {
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.QUESTION -> EmotionType.SUSPICIOUS // 更好奇
            state.userInteraction == UserInteractionType.COMPLEX_QUERY -> EmotionType.PANIC // 更紧张
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.HAPPY // 更高兴
            
            state.taskSuccess == true -> EmotionType.HAPPY // 总是非常高兴
            state.taskSuccess == false -> EmotionType.SUSPICIOUS // 总是明显不满
            
            state.environmentType == EnvironmentType.UNFAMILIAR -> EmotionType.PANIC // 更害怕陌生环境
            state.environmentType == EnvironmentType.OPTIMAL -> EmotionType.HAPPY
            
            else -> state.currentEmotion
        }
    }
} 