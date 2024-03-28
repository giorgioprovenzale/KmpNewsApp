package root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

data class RootState(
    val selectedTab: String,
    val stack: Value<ChildStack<*, Child>>,
    val showBack: Boolean
)
