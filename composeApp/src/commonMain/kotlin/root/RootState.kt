package root

import com.arkivanov.decompose.router.stack.ChildStack

data class RootState(
    val stack: ChildStack<TabsConfig, TabsChild>? = null
)
