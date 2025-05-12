# emotion-kit Usage Examples

## Using in Activity or Fragment

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Get emotion manager instance
        val emotionManager = EmotionStrategyManager.getInstance()
        
        setContent {
            MyTheme {
                RobotEmotionDemo(emotionManager)
            }
        }
    }
}

@Composable
fun RobotEmotionDemo(emotionManager: EmotionStrategyManager) {
    // Observe current emotion state
    val currentEmotion by emotionManager.currentEmotion
    
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display current robot emotion
        Image(
            imageVector = emotionManager.getCurrentEmotionVector(),
            contentDescription = "Robot emotion",
            modifier = Modifier.size(120.dp)
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

## Using in ViewModel

```kotlin
class RobotViewModel : ViewModel() {
    private val emotionManager = EmotionStrategyManager.getInstance()
    
    // Expose emotion state for UI layer to observe
    val currentEmotion = emotionManager.currentEmotion
    
    // Get current emotion vector
    fun getCurrentEmotionVector() = emotionManager.getCurrentEmotionVector()
    
    // Business logic methods
    fun handleUserRequest(isComplex: Boolean) {
        emotionManager.updateUserInteraction(
            if (isComplex) UserInteractionType.COMPLEX_QUERY 
            else UserInteractionType.QUESTION
        )
        
        // Process request...
    }
    
    fun processTask(task: Task): Boolean {
        // Process task...
        val success = /* task processing result */ true
        
        // Update robot emotion
        emotionManager.updateTaskCompletion(
            success = success,
            complexity = determineTaskComplexity(task)
        )
        
        return success
    }
    
    fun updateBatteryStatus(level: Int, isCharging: Boolean) {
        emotionManager.updateBatteryStatus(level, isCharging)
    }
    
    private fun determineTaskComplexity(task: Task): TaskComplexity {
        // Determine complexity based on task characteristics
        return when {
            task.isSimple() -> TaskComplexity.LOW
            task.isComplex() -> TaskComplexity.HIGH
            else -> TaskComplexity.MEDIUM
        }
    }
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