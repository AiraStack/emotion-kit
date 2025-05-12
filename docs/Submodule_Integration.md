# 作为 Submodule 集成

本文档说明如何将 emotion-kit 作为子模块集成到主项目中。

## 1. 添加子模块

在主项目的根目录下执行：

```bash
git submodule add https://github.com/AiraStack/emotion-kit.git
```

## 2. 更新 settings.gradle.kts

在主项目的 settings.gradle.kts 中添加：

```kotlin
include(":emotion-kit")
```

## 3. 添加依赖

在主项目应用模块的 build.gradle.kts 中添加依赖：

```kotlin
dependencies {
    implementation(project(":emotion-kit"))
}
```

## 4. 导入并使用

在主项目的代码中导入并使用 emotion-kit：

```kotlin
import com.airastack.emotionkit.EmotionType
import com.airastack.emotionkit.RobotEmotions
import com.airastack.emotionkit.strategy.EmotionStrategyManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 获取表情管理器
        val emotionManager = EmotionStrategyManager.getInstance()
        
        setContent {
            YourTheme {
                // 使用表情管理器
                RobotInterface(emotionManager)
            }
        }
    }
}

@Composable
fun RobotInterface(emotionManager: EmotionStrategyManager) {
    // 获取当前表情
    val currentEmotion by emotionManager.currentEmotion
    
    // 使用表情
    Image(
        imageVector = emotionManager.getCurrentEmotionVector(),
        contentDescription = "Robot face"
    )
    
    // 其他界面元素...
}
```

## 5. 更新子模块

当子模块有更新时，在主项目中执行：

```bash
git submodule update --remote --merge
```

## 6. 子模块开发

如果需要同时开发主项目和子模块，可以：

1. 切换到子模块目录：
   ```bash
   cd emotion-kit
   ```

2. 切换到适当的分支：
   ```bash
   git checkout main  # 或其他适当的分支
   ```

3. 进行修改并提交

4. 返回主项目并更新引用：
   ```bash
   cd ..
   git add emotion-kit
   git commit -m "更新子模块引用"
   ``` 