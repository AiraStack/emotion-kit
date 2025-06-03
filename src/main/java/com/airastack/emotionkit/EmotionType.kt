package com.airastack.emotionkit

/**
 * Enum defining different emotion types for the robot
 */
enum class EmotionType {
    SUSPICIOUS, // Suspicious/Dissatisfied/Angry
    HAPPY,      // Happy/Friendly
    SATISFIED,  // Satisfied/Calm
    NEUTRAL,    // Neutral/Standby
    PANIC,      // Panic/Emergency/Alarmed
    REPAIR      // Repair/Maintenance
}

fun EmotionType.toRiveState(): String = when (this) {
    EmotionType.SUSPICIOUS -> "suspicious"
    EmotionType.HAPPY -> "happy"
    EmotionType.SATISFIED -> "satisfied"
    EmotionType.NEUTRAL -> "neutral"
    EmotionType.PANIC -> "panic"
    EmotionType.REPAIR -> "repair"
} 
