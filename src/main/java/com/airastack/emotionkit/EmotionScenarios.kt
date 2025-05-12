package com.airastack.emotionkit

/**
 * Types of user interactions
 */
enum class UserInteractionType {
    GREETING,
    QUESTION,
    COMPLEX_QUERY,
    APPRECIATION
}

/**
 * Types of system statuses
 */
enum class SystemStatusType {
    NORMAL,
    ERROR,
    MAINTENANCE,
    LOW_BATTERY,
    CHARGING
}

/**
 * Task complexity levels
 */
enum class TaskComplexity {
    LOW,
    MEDIUM,
    HIGH
}

/**
 * Environment types
 */
enum class EnvironmentType {
    NORMAL,
    HAZARDOUS,
    UNFAMILIAR,
    OPTIMAL
} 