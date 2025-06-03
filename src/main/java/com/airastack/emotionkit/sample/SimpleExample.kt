package com.airastack.emotionkit.sample

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airastack.emotionkit.animation.ExampleAnimation
import com.airastack.emotionkit.animation.ExampleAnimationType

@Composable
fun SimpleExample() {
    val animationType = remember { mutableStateOf<ExampleAnimationType>(ExampleAnimationType.Success) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
    ) {
        ExampleAnimation(
            type = animationType,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        Button(
            onClick = {
                animationType.value = animationType.value.next()
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "Next Type")
        }
    }
}

/**
 * Cycle to the "next" type
 */
private fun ExampleAnimationType.next(): ExampleAnimationType {
    return when (this) {
        is ExampleAnimationType.Idle -> ExampleAnimationType.Handsup
        is ExampleAnimationType.Handsup -> ExampleAnimationType.Fail
        is ExampleAnimationType.Picking -> ExampleAnimationType.Fail
        is ExampleAnimationType.Fail -> ExampleAnimationType.Success
        is ExampleAnimationType.Success -> ExampleAnimationType.Idle
    }
}