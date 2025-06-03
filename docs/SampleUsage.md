# emotion-kit Usage Examples

## Using in Activity or Fragment

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Get emotion manager instance
        val emotionManager = EmotionStrategyManager.getInstance()
        val riveController = emotionManager.getRiveController()
        
        setContent {
            MyTheme {
                RobotEmotionDemo(emotionManager, riveController)
            }
        }
    }
}

@Composable
fun RobotEmotionDemo(
    emotionManager: EmotionStrategyManager,
    riveController: RiveStateController
) {
    // Observe current emotion state
    val currentEmotion by emotionManager.currentEmotion
    
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Rive animation based on current emotion
        RiveAnimation(
            controller = riveController,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        // Switch between different emotions
        Text("Select emotion strategy:", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { emotionManager.switchStrategy("default") }) {
                Text("Default Strategy")
            }
            Button(onClick = { emotionManager.switchStrategy("conservative") }) {
                Text("Conservative Strategy")
            }
            Button(onClick = { emotionManager.switchStrategy("expressive") }) {
                Text("Expressive Strategy")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Simulate different scenarios
        Text("Simulate scenarios:", style = MaterialTheme.typography.titleMedium)
        Button(
            onClick = { emotionManager.updateUserInteraction(UserInteractionType.GREETING) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("User Greeting")
        }
        Button(
            onClick = { emotionManager.updateSystemStatus(SystemStatusType.LOW_BATTERY) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Low Battery Status")
        }
        Button(
            onClick = { 
                emotionManager.updateTaskCompletion(true, TaskComplexity.HIGH) 
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Complete Complex Task")
        }
        Button(
            onClick = { emotionManager.setMaintenanceMode(true) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enter Maintenance Mode")
        }
        Button(
            onClick = { emotionManager.resetState() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset All States")
        }
    }
}
```

## Rive Integration Usage

- The SDK provides a global Rive controller singleton, managed by `EmotionStrategyManager`.
- You can get the controller via `emotionManager.getRiveController()` and use it in your Compose UI:

```kotlin
val emotionManager = EmotionStrategyManager.getInstance()
val riveController = emotionManager.getRiveController()
RiveAnimation(controller = riveController)
```

- When you call any of the following, the Rive animation will automatically update:
    - `emotionManager.setEmotion(EmotionType.HAPPY)`
    - `emotionManager.switchStrategy(...)`
    - `emotionManager.updateUserInteraction(...)` 等

- The mapping between `EmotionType` and Rive state is handled automatically. You only need to use the high-level API.

## Using in ViewModel

```kotlin
class RobotViewModel : ViewModel() {
    private val emotionManager = EmotionStrategyManager.getInstance()
    val currentEmotion = emotionManager.currentEmotion
    fun getCurrentEmotionVector() = emotionManager.getCurrentEmotionVector()
    fun getRiveController() = emotionManager.getRiveController()
    // ... business logic as before
}
```

## Custom Strategy Example

```kotlin
/**
 * Child-friendly emotion strategy - more friendly, positive emotional feedback
 */
class ChildFriendlyEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "Child-Friendly Emotion Strategy"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // Maintenance mode is required
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // More inclined towards positive friendly expressions
        return when {
            // Even for errors, don't use panic expression, switch to suspicious
            state.systemStatus == SystemStatusType.ERROR -> EmotionType.SUSPICIOUS
            
            // Use happy expressions more frequently
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.QUESTION -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.HAPPY
            
            // Use neutral expression for task failure instead of dissatisfied
            state.taskSuccess == false -> EmotionType.NEUTRAL
            state.taskSuccess == true -> EmotionType.HAPPY
            
            // Default to calm expression
            else -> EmotionType.SATISFIED
        }
    }
}

// Register custom strategy
fun registerCustomStrategy() {
    // Assuming EmotionStrategyManager has a method to register custom strategies
    // Actual implementation requires extending EmotionStrategyManager to add this feature
    val customStrategy = ChildFriendlyEmotionStrategy()
    // emotionManager.registerStrategy("child_friendly", customStrategy)
}

> **Note**: For Chinese documentation, please see [SampleUsage_CN.md](SampleUsage_CN.md) 