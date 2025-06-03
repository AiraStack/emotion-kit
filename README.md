# emotion-kit

AI robot emotion management library that provides a flexible emotion system to display appropriate expressions based on different business scenarios.

> **Note**: For Chinese documentation, please see [README_CN.md](README_CN.md) 

## Features

- Six basic expressions: Suspicious/Dissatisfied/Angry, Happy/Friendly, Satisfied/Calm, Neutral/Standby, Panic/Emergency/Alarmed, and Repair/Maintenance
- Emotion management system based on strategy pattern
- Three built-in emotion strategies: Default, Conservative, and Expressive
- Support for multiple business scenarios: User interactions, System status, Task completion, Environment detection, etc.
- Unified cyan-blue expression design (0xFF00CCFF)
- Efficient rendering with Jetpack Compose vector graphics
- **Rive animation support via `animation` package**

## Directory Structure

```
emotion-kit/
  ├── src/
  │   └── main/
  │       ├── java/com/airastack/emotionkit/
  │       │   ├── animation/      # Rive animation & controller (was components/)
  │       │   ├── strategy/       # Emotion strategies
  │       │   ├── theme/          # Compose theme
  │       │   ├── sample/         # Usage samples
  │       │   ├── EmotionType.kt  # Core types
  │       │   ├── RobotEmotions.kt
  │       │   └── EmotionScenarios.kt
  │       └── res/
  ├── docs/
  ├── README.md
  ├── README_CN.md
  └── LICENSE
```

## Installation

### Use as Git Submodule

```bash
# Add as submodule
git submodule add https://github.com/AiraStack/emotion-kit.git

# Include in settings.gradle.kts
include(":emotion-kit")
```

Add the dependency in your application module's build.gradle.kts:

```kotlin
dependencies {
    implementation(project(":emotion-kit"))
}
```

## Quick Start

### Basic Usage

```kotlin
import com.airastack.emotionkit.strategy.EmotionStrategyManager
import com.airastack.emotionkit.animation.RiveStateController

val emotionManager = EmotionStrategyManager.getInstance()
val riveController = emotionManager.getRiveController()

// In Compose
RiveAnimation(
    stateController = riveController
)
```

### Update Emotion State

```kotlin
// User interaction scenario
emotionManager.updateUserInteraction(UserInteractionType.GREETING)

// System status scenario
emotionManager.updateSystemStatus(SystemStatusType.LOW_BATTERY)

// Task completion scenario
emotionManager.updateTaskCompletion(success = true, complexity = TaskComplexity.HIGH)

// Directly set emotion (override strategy)
emotionManager.setEmotion(EmotionType.HAPPY)
```

### Switch Emotion Strategy

```kotlin
// Switch to conservative strategy
emotionManager.switchStrategy("conservative")

// Switch to expressive strategy
emotionManager.switchStrategy("expressive")

// Get current strategy name
val strategyName = emotionManager.getCurrentStrategyName()

// Get all available strategies
val strategies = emotionManager.getAvailableStrategies()
```

## Rive Animation Example

```kotlin
import com.airastack.emotionkit.animation.ExampleAnimation
import com.airastack.emotionkit.animation.ExampleAnimationType

@Composable
fun SimpleExample() {
    val animationType = remember { mutableStateOf<ExampleAnimationType>(ExampleAnimationType.Success) }
    ExampleAnimation(type = animationType)
}
```

## Emotion Strategies

### Default Strategy (DefaultEmotionStrategy)

Direct mapping based on current state, returning the appropriate emotion for different scenarios. Priority handling:
1. Maintenance mode -> REPAIR
2. System error -> PANIC
3. Low battery -> SUSPICIOUS
4. Other states mapped according to scenarios

### Conservative Strategy (ConservativeEmotionStrategy)

Biased towards using neutral expressions, not easily showing extreme emotions, maintaining more conservative expressions even in special situations.

### Expressive Strategy (ExpressiveEmotionStrategy)

More diverse expression changes, stronger reactions, and more obvious emotional responses to various states.

## Custom Emotion Strategy

Create custom emotion strategies by implementing the `EmotionStrategy` interface:

```kotlin
class MyCustomStrategy : EmotionStrategy {
    override val strategyName: String = "Custom Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // Implement custom logic
        return EmotionType.NEUTRAL
    }
}
```

## Sample Code

### Displaying All Emotions in Main Interface

```kotlin
@Composable
fun RobotEmotionsScreen() {
    var selectedEmotion by remember { mutableStateOf(EmotionType.NEUTRAL) }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display currently selected emotion
        Image(
            imageVector = RobotEmotions.getEmotion(selectedEmotion),
            contentDescription = "Robot face"
        )
        
        // Emotion selection grid
        EmotionGrid(
            onEmotionSelected = { selectedEmotion = it }
        )
    }
}
```

## License

Apache License
