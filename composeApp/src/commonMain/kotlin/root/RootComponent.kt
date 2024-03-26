package root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ui.news.detail.DetailComponent
import ui.news.list.ListComponent
import list2.ListComponent2

interface RootComponent {

    val homeTabStack: Value<ChildStack<*, Child>>
    val sourcesTabStack: Value<ChildStack<*, Child>>

    fun onBackClicked()
}
