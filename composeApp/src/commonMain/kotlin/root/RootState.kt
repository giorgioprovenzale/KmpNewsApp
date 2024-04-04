package root

import com.arkivanov.decompose.router.stack.ChildStack

data class RootState(
    val selectedTab: String? = null,
    val stack: ChildStack<TabsConfig, TabsChild>? = null
)
