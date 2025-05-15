package com.airastack.emotionkit

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * A utility class that provides access to robot emotion vectors
 */
object RobotEmotions {
    // Define unified cyan-blue color for the robot
    private val robotCyanBlue = Color(0xFF00CCFF)
    
    /**
     * Gets the appropriate emotion vector based on the emotion type
     */
    fun getEmotion(emotionType: EmotionType): ImageVector {
        return when (emotionType) {
            EmotionType.SUSPICIOUS -> suspiciousFace
            EmotionType.HAPPY -> happyFace
            EmotionType.SATISFIED -> satisfiedFace
            EmotionType.NEUTRAL -> neutralFace
            EmotionType.PANIC -> panicFace
            EmotionType.REPAIR -> repairFace
        }
    }

    // Suspicious/Dissatisfied/Angry
    val suspiciousFace = ImageVector.Builder(
        name = "RobotSuspiciousFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (small square)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(44f, 48f, 52f, 56f)
        }

        // Right eye (small square)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(72f, 48f, 80f, 56f)
        }

        // Eyebrows (diagonal lines) - angry expression
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(42f, 40f)
            lineTo(52f, 44f)
        }

        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(76f, 40f)
            lineTo(86f, 44f)
        }

        // Mouth (unhappy line)
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(50f, 76f)
            lineTo(78f, 76f)
        }
    }.build()

    // Happy/Friendly
    val happyFace = ImageVector.Builder(
        name = "RobotHappyFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (circular)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            moveTo(48f, 52f)
            lineTo(48f, 48f)
            lineTo(52f, 48f)
            lineTo(52f, 52f)
            close()
        }

        // Right eye (circular)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            moveTo(76f, 52f)
            lineTo(76f, 48f)
            lineTo(80f, 48f)
            lineTo(80f, 52f)
            close()
        }

        // Mouth (smile - simulating curve with multiple line segments)
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(50f, 76f)
            lineTo(57f, 80f)
            lineTo(64f, 82f)
            lineTo(71f, 80f)
            lineTo(78f, 76f)
        }
    }.build()

    // Satisfied/Calm
    val satisfiedFace = ImageVector.Builder(
        name = "RobotSatisfiedFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (circular)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            moveTo(44f, 52f)
            lineTo(44f, 48f)
            lineTo(52f, 48f)
            lineTo(52f, 52f)
            close()
        }

        // Right eye (circular)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            moveTo(72f, 52f)
            lineTo(72f, 48f)
            lineTo(80f, 48f)
            lineTo(80f, 52f)
            close()
        }

        // Mouth (mild smile - simulating slight curve with multiple line segments)
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(50f, 76f)
            lineTo(57f, 77f)
            lineTo(64f, 78f)
            lineTo(71f, 77f)
            lineTo(78f, 76f)
        }
    }.build()

    // Neutral/Standby
    val neutralFace = ImageVector.Builder(
        name = "RobotNeutralFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (square, blue for standby state)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(44f, 48f, 52f, 56f)
        }

        // Right eye (square, blue for standby state)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(72f, 48f, 80f, 56f)
        }

        // Mouth (neutral line)
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 3f
        ) {
            moveTo(50f, 76f)
            lineTo(78f, 76f)
        }
    }.build()

    // Panic/Emergency/Alarmed
    val panicFace = ImageVector.Builder(
        name = "RobotPanicFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (exclamation mark shape)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(44f, 40f, 52f, 52f)
            moveTo(44f, 56f)
            lineTo(52f, 56f)
            lineTo(52f, 58f)
            lineTo(44f, 58f)
            close()
        }

        // Right eye (exclamation mark shape)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(72f, 40f, 80f, 52f)
            moveTo(72f, 56f)
            lineTo(80f, 56f)
            lineTo(80f, 58f)
            lineTo(72f, 58f)
            close()
        }

        // Mouth (surprised O shape)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            moveTo(59f, 76f)
            lineTo(59f, 70f)
            lineTo(69f, 70f)
            lineTo(69f, 76f)
            close()
        }
    }.build()

    // Repair/Maintenance
    val repairFace = ImageVector.Builder(
        name = "RobotRepairFace",
        defaultWidth = 128.dp,
        defaultHeight = 128.dp,
        viewportWidth = 128f,
        viewportHeight = 128f
    ).apply {
        // Face mask (black)
        path(
            fill = SolidColor(Color.Black)
        ) {
            RoundRect(Rect(16f, 16f, 112f, 96f), 16f, 16f)
        }

        // Left eye (wrench shape for repair)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(40f, 44f, 56f, 48f)
            Rect(44f, 48f, 48f, 60f)
        }

        // Right eye (screwdriver shape for repair)
        path(
            fill = SolidColor(robotCyanBlue)
        ) {
            Rect(72f, 44f, 88f, 48f)
            Rect(78f, 48f, 82f, 60f)
        }

        // Mouth (horizontal stripes indicating repair state)
        path(
            stroke = SolidColor(robotCyanBlue),
            strokeLineWidth = 2f
        ) {
            moveTo(50f, 72f)
            lineTo(78f, 72f)
            moveTo(50f, 76f)
            lineTo(78f, 76f)
            moveTo(50f, 80f)
            lineTo(78f, 80f)
        }
    }.build()
} 