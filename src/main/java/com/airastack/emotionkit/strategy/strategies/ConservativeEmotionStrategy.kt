package com.airastack.emotionkit.strategy.strategies

import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.EmotionState
import com.airastack.emotionkit.strategy.EmotionStrategy

/**
 * 保守表情策略 - 偏向使用中性表情，不轻易显示极端情绪
 */
class ConservativeEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "保守表情策略"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // 即使在特殊情况下，也保持更加保守的表情
        
        // 维护模式是必须的
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // 其他情况更倾向于使用中性或满意表情
        return when {
            state.systemStatus == SystemStatusType.ERROR -> EmotionType.SUSPICIOUS  // 不使用PANIC
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.SATISFIED // 不使用HAPPY
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.SATISFIED
            state.taskSuccess == true -> EmotionType.SATISFIED
            state.taskSuccess == false -> EmotionType.NEUTRAL // 失败也不表现得太明显
            state.environmentType == EnvironmentType.HAZARDOUS -> EmotionType.SUSPICIOUS // 不使用PANIC
            else -> EmotionType.NEUTRAL
        }
    }
} 