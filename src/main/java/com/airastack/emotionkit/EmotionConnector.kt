package com.airastack.emotionkit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.airastack.emotionkit.strategy.EmotionStrategyManager

/**
 * 情绪连接器类，提供简单的公共API接口，将yuanyin项目功能连接到emotion-kit
 */
object EmotionConnector {
    
    /**
     * 获取当前情绪状态
     */
    fun getCurrentEmotion(): EmotionType {
        return EmotionStrategyManager.getInstance().currentEmotion.value
    }
    
    /**
     * 获取当前情绪矢量图
     */
    fun getCurrentEmotionVector(): ImageVector {
        return EmotionStrategyManager.getInstance().getCurrentEmotionVector()
    }
    
    /**
     * 设置情绪
     */
    fun setEmotion(emotionType: EmotionType) {
        EmotionStrategyManager.getInstance().setEmotion(emotionType)
    }
    
    /**
     * 处理用户交互场景
     */
    fun handleUserInteraction(interactionType: UserInteractionType) {
        EmotionStrategyManager.getInstance().updateUserInteraction(interactionType)
    }
    
    /**
     * 处理系统状态场景
     */
    fun handleSystemStatus(statusType: SystemStatusType) {
        EmotionStrategyManager.getInstance().updateSystemStatus(statusType)
    }
    
    /**
     * 处理任务完成场景
     */
    fun handleTaskCompletion(success: Boolean, complexity: TaskComplexity = TaskComplexity.MEDIUM) {
        EmotionStrategyManager.getInstance().updateTaskCompletion(success, complexity)
    }
    
    /**
     * 处理环境检测场景
     */
    fun handleEnvironmentDetection(environmentType: EnvironmentType) {
        EmotionStrategyManager.getInstance().updateEnvironment(environmentType)
    }
    
    /**
     * 切换情绪策略
     */
    fun switchStrategy(strategyKey: String): Boolean {
        return EmotionStrategyManager.getInstance().switchStrategy(strategyKey)
    }
    
    /**
     * 获取当前策略名称
     */
    fun getCurrentStrategyName(): String {
        return EmotionStrategyManager.getInstance().getCurrentStrategyName()
    }
    
    /**
     * 也可以使用更简单的表情管理器
     */
    private val simpleManager by lazy { SimpleEmotionManager.getInstance() }
    
    /**
     * 使用简单情绪管理器处理用户交互
     */
    fun simpleHandleUserInteraction(interactionType: UserInteractionType) {
        simpleManager.handleUserInteraction(interactionType)
    }
    
    /**
     * 使用简单情绪管理器处理系统状态
     */
    fun simpleHandleSystemStatus(systemStatus: SystemStatusType) {
        simpleManager.handleSystemStatus(systemStatus)
    }
    
    /**
     * 使用简单情绪管理器处理任务完成
     */
    fun simpleHandleTaskCompletion(success: Boolean, complexity: TaskComplexity = TaskComplexity.MEDIUM) {
        simpleManager.handleTaskCompletion(success, complexity)
    }
    
    /**
     * 使用简单情绪管理器处理环境检测
     */
    fun simpleHandleEnvironmentDetection(environmentType: EnvironmentType) {
        simpleManager.handleEnvironmentDetection(environmentType)
    }
} 