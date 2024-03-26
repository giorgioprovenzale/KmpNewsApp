package root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val homeTabStack: Value<ChildStack<*, Child>>
    val sourcesTabStack: Value<ChildStack<*, Child>>

    fun onBackClicked()
}
