package com.airastack.emotionkit.strategy

import androidx.compose.runtime.mutableStateOf
import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.EnvironmentType
import com.airastack.emotionkit.RobotEmotions
import com.airastack.emotionkit.SystemStatusType
import com.airastack.emotionkit.TaskComplexity
import com.airastack.emotionkit.UserInteractionType
import com.airastack.emotionkit.strategy.strategies.ConservativeEmotionStrategy
import com.airastack.emotionkit.strategy.strategies.DefaultEmotionStrategy
import com.airastack.emotionkit.strategy.strategies.ExpressiveEmotionStrategy

/**
 * Emotion Strategy Manager - responsible for managing different emotion strategies and providing the current emotion
 */
class EmotionStrategyManager {
    // List of available strategies
    private val availableStrategies = mapOf(
        "default" to DefaultEmotionStrategy(),
        "conservative" to ConservativeEmotionStrategy(),
        "expressive" to ExpressiveEmotionStrategy()
    )
    
    // Currently used strategy
    private var currentStrategy = availableStrategies["default"]!!
    
    // Current emotion state
    private var currentState = EmotionState()
    
    // Current emotion, observable
    val currentEmotion = mutableStateOf(EmotionType.NEUTRAL)
    
    /**
     * Switch to the specified emotion strategy
     */
    fun switchStrategy(strategyKey: String): Boolean {
        val strategy = availableStrategies[strategyKey] ?: return false
        currentStrategy = strategy
        // Apply new strategy to current state
        updateEmotion()
        return true
    }
    
    /**
     * Get current strategy name
     */
    fun getCurrentStrategyName(): String {
        return currentStrategy.strategyName
    }
    
    /**
     * Get names and keys of all available strategies
     */
    fun getAvailableStrategies(): List<Pair<String, String>> {
        return availableStrategies.map { (key, strategy) -> 
            Pair(key, strategy.strategyName)
        }
    }
    
    /**
     * Update emotion state and apply current strategy
     */
    private fun updateEmotion() {
        // Update current emotion
        val newEmotion = currentStrategy.getEmotionForState(currentState.copy(
            currentEmotion = currentEmotion.value
        ))
        
        if (newEmotion != currentEmotion.value) {
            currentEmotion.value = newEmotion
        }
    }
    
    /**
     * Get the ImageVector for the current emotion
     */
    fun getCurrentEmotionVector() = RobotEmotions.getEmotion(currentEmotion.value)
    
    /**
     * Methods for updating various states below
     */
    
    // Update user interaction state
    fun updateUserInteraction(interaction: UserInteractionType?) {
        currentState = currentState.copy(userInteraction = interaction)
        updateEmotion()
    }
    
    // Update system status
    fun updateSystemStatus(status: SystemStatusType?) {
        currentState = currentState.copy(systemStatus = status)
        updateEmotion()
    }
    
    // Update task completion status
    fun updateTaskCompletion(success: Boolean?, complexity: TaskComplexity = TaskComplexity.MEDIUM) {
        currentState = currentState.copy(
            taskSuccess = success,
            taskComplexity = complexity
        )
        updateEmotion()
    }
    
    // Update environment status
    fun updateEnvironment(environment: EnvironmentType?) {
        currentState = currentState.copy(environmentType = environment)
        updateEmotion()
    }
    
    // Update battery status
    fun updateBatteryStatus(level: Int?, isCharging: Boolean?) {
        currentState = currentState.copy(
            batteryLevel = level,
            isCharging = isCharging
        )
        updateEmotion()
    }
    
    // Update maintenance mode
    fun setMaintenanceMode(inMaintenance: Boolean) {
        currentState = currentState.copy(isInMaintenanceMode = inMaintenance)
        updateEmotion()
    }
    
    // Reset all states
    fun resetState() {
        currentState = EmotionState()
        updateEmotion()
    }
    
    // Directly set emotion (override strategy)
    fun setEmotion(emotionType: EmotionType) {
        currentEmotion.value = emotionType
    }
    
    companion object {
        // Singleton pattern
        private var instance: EmotionStrategyManager? = null
        
        fun getInstance(): EmotionStrategyManager {
            if (instance == null) {
                instance = EmotionStrategyManager()
            }
            return instance!!
        }
    }
} 