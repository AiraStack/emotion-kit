# emotion-kit 使用示例

## 在 Activity 或 Fragment 中使用

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 获取表情管理器实例
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
    // 观察当前表情状态
    val currentEmotion by emotionManager.currentEmotion
    
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 显示当前机器人表情
        Image(
            imageVector = emotionManager.getCurrentEmotionVector(),
            contentDescription = "Robot emotion",
            modifier = Modifier.size(120.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 切换不同情绪
        Text("选择表情策略:", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { emotionManager.switchStrategy("default") }) {
                Text("默认策略")
            }
            
            Button(onClick = { emotionManager.switchStrategy("conservative") }) {
                Text("保守策略")
            }
            
            Button(onClick = { emotionManager.switchStrategy("expressive") }) {
                Text("情绪化策略")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 模拟不同场景
        Text("模拟场景:", style = MaterialTheme.typography.titleMedium)
        
        Button(
            onClick = { emotionManager.updateUserInteraction(UserInteractionType.GREETING) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("用户打招呼")
        }
        
        Button(
            onClick = { emotionManager.updateSystemStatus(SystemStatusType.LOW_BATTERY) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("低电量状态")
        }
        
        Button(
            onClick = { 
                emotionManager.updateTaskCompletion(true, TaskComplexity.HIGH) 
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("完成复杂任务")
        }
        
        Button(
            onClick = { emotionManager.setMaintenanceMode(true) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("进入维护模式")
        }
        
        Button(
            onClick = { emotionManager.resetState() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("重置所有状态")
        }
    }
}
```

## 在 ViewModel 中使用

```kotlin
class RobotViewModel : ViewModel() {
    private val emotionManager = EmotionStrategyManager.getInstance()
    
    // 暴露情绪状态供 UI 层观察
    val currentEmotion = emotionManager.currentEmotion
    
    // 获取当前表情矢量图
    fun getCurrentEmotionVector() = emotionManager.getCurrentEmotionVector()
    
    // 业务逻辑方法
    fun handleUserRequest(isComplex: Boolean) {
        emotionManager.updateUserInteraction(
            if (isComplex) UserInteractionType.COMPLEX_QUERY 
            else UserInteractionType.QUESTION
        )
        
        // 处理请求...
    }
    
    fun processTask(task: Task): Boolean {
        // 处理任务...
        val success = /* 任务处理结果 */ true
        
        // 更新机器人表情
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
        // 根据任务特性确定复杂度
        return when {
            task.isSimple() -> TaskComplexity.LOW
            task.isComplex() -> TaskComplexity.HIGH
            else -> TaskComplexity.MEDIUM
        }
    }
}
```

## 自定义策略示例

```kotlin
/**
 * 儿童模式表情策略 - 更加友好、积极的表情反馈
 */
class ChildFriendlyEmotionStrategy : EmotionStrategy {
    override val strategyName: String = "儿童友好表情策略"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // 维护模式是必须的
        if (state.isInMaintenanceMode) {
            return EmotionType.REPAIR
        }
        
        // 更偏向积极友好的表情
        return when {
            // 即使是错误，也不使用惊慌表情，改用怀疑表情
            state.systemStatus == SystemStatusType.ERROR -> EmotionType.SUSPICIOUS
            
            // 更频繁地使用开心表情
            state.userInteraction == UserInteractionType.GREETING -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.QUESTION -> EmotionType.HAPPY
            state.userInteraction == UserInteractionType.APPRECIATION -> EmotionType.HAPPY
            
            // 任务失败使用中性表情而不是不满
            state.taskSuccess == false -> EmotionType.NEUTRAL
            state.taskSuccess == true -> EmotionType.HAPPY
            
            // 默认使用平和表情
            else -> EmotionType.SATISFIED
        }
    }
}

// 注册自定义策略
fun registerCustomStrategy() {
    // 这里假设 EmotionStrategyManager 有注册自定义策略的方法
    // 实际使用需要扩展 EmotionStrategyManager 以添加此功能
    val customStrategy = ChildFriendlyEmotionStrategy()
    // emotionManager.registerStrategy("child_friendly", customStrategy)
} 