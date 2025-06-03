# Submodule Integration

This document explains how to integrate emotion-kit as a submodule into your main project. 
> **Note**: For Chinese documentation, please see [Submodule_Integration_CN.md](Submodule_Integration_CN.md)

## 1. Add Submodule

Execute in the root directory of the main project:

```bash
git submodule add https://github.com/AiraStack/emotion-kit.git
```

## 2. Update settings.gradle.kts

Add to the main project's settings.gradle.kts:

```kotlin
include(":emotion-kit")
```

## 3. Add Dependency

Add dependency in the main project's application module build.gradle.kts:

```kotlin
dependencies {
    implementation(project(":emotion-kit"))
}
```

## 4. Import and Use

Import and use emotion-kit in the main project's code:

```kotlin
import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.RobotEmotions
import com.airastack.emotionkit.strategy.EmotionStrategyManager
import com.airastack.emotionkit.animation.RiveStateController
import com.airastack.emotionkit.animation.RiveAnimation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Get emotion manager
        val emotionManager = EmotionStrategyManager.getInstance()
        val riveController = emotionManager.getRiveController()
        
        setContent {
            YourTheme {
                // Use emotion manager and animation
                RobotInterface(emotionManager, riveController)
            }
        }
    }
}

@Composable
fun RobotInterface(emotionManager: EmotionStrategyManager, riveController: RiveStateController) {
    // Get current emotion
    val currentEmotion by emotionManager.currentEmotion
    
    // Use emotion
    RiveAnimation(
        stateController = riveController
    )
    // Other interface elements...
}
```

## 5. Update Submodule

When the submodule has updates, execute in the main project:

```bash
git submodule update --remote --merge
```

## 6. Submodule Development

If you need to develop the main project and submodule simultaneously:

1. Switch to the submodule directory:
   ```bash
   cd emotion-kit
   ```

2. Switch to the appropriate branch:
   ```bash
   git checkout main  # or other appropriate branch
   ```

3. Make changes and commit

4. Return to the main project and update the reference:
   ```bash
   cd ..
   git add emotion-kit
   git commit -m "Update submodule reference"
   ```
