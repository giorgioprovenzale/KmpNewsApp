package ui.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.models.Category
import extensions.capitalized
import theme.spacing_1x
import theme.spacing_2x

@Composable
fun CategoriesList(categories: List<Category>, onItemClicked: (Category) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 64.dp)
    ) {

        items(categories) { category ->
            CategoryItem(
                category = category,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
fun CategoryItem(category: Category, onItemClicked: (Category) -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(spacing_1x).fillMaxWidth().clickable {
            onItemClicked(category)
        },
        elevation = 2.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                category.name.capitalized(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(all = spacing_2x)
            )
        }
    }
}