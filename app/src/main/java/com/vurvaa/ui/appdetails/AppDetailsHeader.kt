package com.vurvaa.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vurvaa.R
import com.vurvaa.domain.AppDetails
import com.vurvaa.domain.Category
import kotlin.math.roundToInt

@Composable
fun AppDetailsHeader(
    appDetails: AppDetails,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = appDetails.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_app_game),
            error = painterResource(R.drawable.ic_app_game),
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = getCategoryText(appDetails.category),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = appDetails.name,
                modifier = Modifier.testTag("app_details_name"),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = appDetails.developer,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(4.dp))
            Row {
                Column(Modifier.width(IntrinsicSize.Max)) {
                    Text(
                        text = "${appDetails.ageRating}+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = stringResource(R.string.app_details_age))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(text = "${appDetails.size.roundToInt()} MB")
                    Spacer(Modifier.height(4.dp))
                    Text(text = stringResource(R.string.app_details_size))
                }
            }
        }
    }
}

// Названия категорий сохранены из практического примера.
@Composable
private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложения"
    Category.GAME -> "Игры"
    Category.PRODUCTIVITY -> "Производительность"
    Category.SOCIAL -> "Социальные сети"
    Category.EDUCATION -> "Образование"
    Category.ENTERTAINMENT -> "Развлечения"
    Category.MUSIC -> "Музыка"
    Category.VIDEO -> "Видео"
    Category.PHOTOGRAPHY -> "Фотография"
    Category.HEALTH -> "Здоровье"
    Category.SPORTS -> "Спорт"
    Category.NEWS -> "Новости"
    Category.BOOKS -> "Книги"
    Category.BUSINESS -> "Бизнес"
    Category.FINANCE -> "Финансы"
    Category.TRAVEL -> "Путешествия"
    Category.MAPS -> "Карты"
    Category.FOOD -> "Еда"
    Category.SHOPPING -> "Покупки"
    Category.UTILITIES -> "Утилиты"
}


