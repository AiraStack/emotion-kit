# emotion-kit

AI 机器人表情管理库，提供了一套灵活的表情系统，可以根据不同的业务场景展示对应的表情。

## 目录结构

```
emotion-kit/
  ├── src/
  │   └── main/
  │       ├── java/com/airastack/emotionkit/
  │       │   ├── animation/      # Rive 动画与控制器（原 components/）
  │       │   ├── strategy/       # 情感策略
  │       │   ├── theme/          # Compose 主题
  │       │   ├── sample/         # 示例
  │       │   ├── EmotionType.kt  # 核心类型
  │       │   ├── RobotEmotions.kt
  │       │   └── EmotionScenarios.kt
  │       └── res/
  ├── docs/
  ├── README.md
  ├── README_CN.md
  └── LICENSE
```

## 功能特性

- 六种基本表情：怀疑/不满/生气、开心/友善、满意/平和、中性/待机、跌落/紧急/惊慌、清洁/维修状态
- 基于策略模式的情绪管理系统
- 三种内置表情策略：默认策略、保守策略和情绪化策略
- 支持多种业务场景：用户交互、系统状态、任务完成、环境检测等
- 统一的青蓝色表情设计 (0xFF00CCFF)
- Jetpack Compose 矢量图形实现，高效渲染

## 安装

### 作为 Git Submodule 使用

```bash
# 添加为子模块
git submodule add https://github.com/AiraStack/emotion-kit.git

# 在 settings.gradle.kts 中引入
include(":emotion-kit")
```

在你的应用模块的 build.gradle.kts 中添加依赖：

```kotlin
dependencies {
    implementation(project(":emotion-kit"))
}
```

## 快速开始

```kotlin
import com.airastack.emotionkit.strategy.EmotionStrategyManager
import com.airastack.emotionkit.animation.RiveStateController

val emotionManager = EmotionStrategyManager.getInstance()
val riveController = emotionManager.getRiveController()

// 在 Compose 中使用
RiveAnimation(
    stateController = riveController
)
```

## Rive 动画示例

```kotlin
import com.airastack.emotionkit.animation.ExampleAnimation
import com.airastack.emotionkit.animation.ExampleAnimationType

@Composable
fun SimpleExample() {
    val animationType = remember { mutableStateOf<ExampleAnimationType>(ExampleAnimationType.Success) }
    ExampleAnimation(type = animationType)
}
```

## 表情策略

### 默认策略 (DefaultEmotionStrategy)

基于当前状态的直接映射，根据不同情况返回对应表情。优先级处理：
1. 维护模式 -> REPAIR
2. 系统错误 -> PANIC
3. 低电量 -> SUSPICIOUS
4. 其他状态根据场景映射

### 保守策略 (ConservativeEmotionStrategy)

偏向使用中性表情，不轻易显示极端情绪，即使在特殊情况下也保持更加保守的表情。

### 情绪化策略 (ExpressiveEmotionStrategy)

表情变化更丰富，反应更强烈，对各种状态都有更加明显的情绪表现。

## 自定义表情策略

通过实现 `EmotionStrategy` 接口来创建自定义表情策略：

```kotlin
class MyCustomStrategy : EmotionStrategy {
    override val strategyName: String = "自定义策略"
    
    override fun getEmotionForState(state: EmotionState): EmotionType {
        // 实现自定义逻辑
        return EmotionType.NEUTRAL
    }
}
```

## 示例代码

### 主界面中展示所有表情

```kotlin
@Composable
fun RobotEmotionsScreen() {
    var selectedEmotion by remember { mutableStateOf(EmotionType.NEUTRAL) }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 显示当前选中的表情
        Image(
            imageVector = RobotEmotions.getEmotion(selectedEmotion),
            contentDescription = "Robot face"
        )
        
        // 表情选择网格
        EmotionGrid(
            onEmotionSelected = { selectedEmotion = it }
        )
    }
}
```

## 文档

- [子模块集成指南](docs/Submodule_Integration.md)
- [使用示例](SampleUsage.md)

## 许可证

Apache License 