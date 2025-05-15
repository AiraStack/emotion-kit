package com.airastack.emotionkit.image

import android.content.Context
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.loadXmlImageVector
import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.RobotEmotions

/**
 * A utility class that provides access to robot emotion images
 * This class handles loading SVG images from assets
 */
object EmotionImageLoader {
    private const val ASSETS_PATH = "emotion"
    
    /**
     * Gets the appropriate emotion image path based on the emotion type
     */
    fun getEmotionImagePath(emotionType: EmotionType): String {
        return when (emotionType) {
            EmotionType.SUSPICIOUS -> "$ASSETS_PATH/suspicious.svg"
            EmotionType.HAPPY -> "$ASSETS_PATH/happy.svg"
            EmotionType.SATISFIED -> "$ASSETS_PATH/satisfied.svg"
            EmotionType.NEUTRAL -> "$ASSETS_PATH/neutral.svg"
            EmotionType.PANIC -> "$ASSETS_PATH/panic.svg"
            EmotionType.REPAIR -> "$ASSETS_PATH/repair.svg"
        }
    }
    
    /**
     * Loads an SVG image from assets and converts it to ImageVector
     * Falls back to vector graphics if image loading fails
     */
    fun loadEmotionImage(context: Context, emotionType: EmotionType): ImageVector {
        val path = getEmotionImagePath(emotionType)
        return try {
            context.assets.open(path).use { inputStream ->
                loadXmlImageVector(inputStream, context.resources)
            }
        } catch (e: Exception) {
            // Fallback to default vector if image loading fails
            RobotEmotions.getFallbackEmotion(emotionType)
        }
    }
} 