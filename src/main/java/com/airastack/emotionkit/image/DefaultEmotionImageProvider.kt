package com.airastack.emotionkit.image

import android.content.Context
import androidx.compose.ui.graphics.vector.ImageVector
import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.RobotEmotions

/**
 * 默认的表情图片提供者
 * 这是 emotion-kit 库的主要图片提供接口
 */
class DefaultEmotionImageProvider private constructor() {
    companion object {
        @Volatile
        private var instance: DefaultEmotionImageProvider? = null
        
        fun getInstance(): DefaultEmotionImageProvider {
            return instance ?: synchronized(this) {
                instance ?: DefaultEmotionImageProvider().also { instance = it }
            }
        }
    }
    
    /**
     * 获取表情图片向量
     * 使用 RobotEmotions 来加载图片，支持 SVG 和矢量图形回退
     */
    fun getEmotionVector(emotionType: EmotionType): ImageVector {
        return RobotEmotions.getEmotion(emotionType)
    }
} 