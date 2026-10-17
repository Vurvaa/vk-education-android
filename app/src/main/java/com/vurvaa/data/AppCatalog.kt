package com.vurvaa.data

import com.vurvaa.R
import com.vurvaa.domain.AppDetails
import com.vurvaa.domain.Category

/** Local data in the AppDetails format from maltsevmicyou/VkEducation. */
object AppCatalog {
    const val SHOWCASE_PAGE_COUNT = 3
    const val APPS_PER_PAGE = 3

    val apps: List<AppDetails> = listOf(
        AppDetails(
            id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
            name = "Гильдия Героев: Экшен ММО РПГ",
            developer = "VK Play",
            category = Category.GAME,
            ageRating = 12,
            size = 223.7f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_game}",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
                "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
                "https://static.rustore.ru/imgproxy/g5whSI1uNqaL2TUO7TFfM8M63vXpWXNCm2vlX4Ahvc4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/c2dde8bc-c4ab-482a-80a5-2789149f598d.jpg@webp",
            ),
            description = "Легендарный рейд героев в фэнтези РПГ. Станьте героем гильдии " +
                "и сразите мастера подземелья! Исследуйте волшебный мир, собирайте " +
                "снаряжение и объединяйтесь с друзьями для совместных приключений.",
        ),
        AppDetails(
            id = "quicknote",
            name = "QuickNote",
            developer = "QuickNote Studio",
            category = Category.PRODUCTIVITY,
            ageRating = 0,
            size = 18.4f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_notes}",
            screenshotUrlList = emptyList(),
            description = "Сохраняйте идеи, составляйте списки и записывайте важное. " +
                "Сортируйте заметки по папкам и находите нужные записи с помощью поиска. " +
                "Заметки всегда под рукой, даже без подключения к интернету.",
        ),
        AppDetails(
            id = "calmalist",
            name = "Calmalist",
            developer = "Calm Studio",
            category = Category.HEALTH,
            ageRating = 3,
            size = 42.1f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_calm}",
            screenshotUrlList = emptyList(),
            description = "Найдите время для отдыха: дыхательные упражнения, медитации " +
                "и звуки природы помогут сделать паузу. Выбирайте занятия по настроению " +
                "и сохраняйте понравившиеся практики в избранное.",
        ),
        AppDetails(
            id = "fitriver",
            name = "FitRiver",
            developer = "River Apps",
            category = Category.SPORTS,
            ageRating = 3,
            size = 65.2f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_fitness}",
            screenshotUrlList = emptyList(),
            description = "Планируйте тренировки и следите за своим прогрессом. " +
                "Выбирайте упражнения для дома и зала, записывайте результаты " +
                "и двигайтесь к личным целям в удобном темпе.",
        ),
        AppDetails(
            id = "taskloop",
            name = "TaskLoop",
            developer = "Loop Team",
            category = Category.PRODUCTIVITY,
            ageRating = 0,
            size = 24.8f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_tasks}",
            screenshotUrlList = emptyList(),
            description = "Организуйте повседневные дела в одном месте. Создавайте задачи, " +
                "назначайте сроки и отмечайте выполненное. Повторяющиеся списки " +
                "помогут поддерживать полезные привычки и ничего не забывать.",
        ),
        AppDetails(
            id = "pixtrix",
            name = "Pixtrix",
            developer = "Pixel Lab",
            category = Category.PHOTOGRAPHY,
            ageRating = 3,
            size = 87.3f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_photo}",
            screenshotUrlList = emptyList(),
            description = "Редактируйте любимые фотографии: настраивайте свет и цвет, " +
                "применяйте фильтры и собирайте коллажи. Сохраняйте готовые снимки " +
                "и делитесь ими с друзьями.",
        ),
        AppDetails(
            id = "tinychef",
            name = "TinyChef",
            developer = "Tiny Kitchen",
            category = Category.FOOD,
            ageRating = 0,
            size = 36.5f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_food}",
            screenshotUrlList = emptyList(),
            description = "Открывайте новые рецепты на каждый день. Выбирайте блюда " +
                "по доступным ингредиентам, сохраняйте любимое и составляйте список " +
                "покупок. Пошаговые инструкции помогут приготовить первый ужин.",
        ),
        AppDetails(
            id = "gymmate",
            name = "GymMate",
            developer = "GymMate Team",
            category = Category.SPORTS,
            ageRating = 3,
            size = 51.6f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_gym}",
            screenshotUrlList = emptyList(),
            description = "Ваш дневник занятий в тренажёрном зале. Записывайте подходы " +
                "и повторения, сохраняйте программы и сравнивайте результаты. " +
                "Встроенный таймер подскажет, когда начинать следующий подход.",
        ),
        AppDetails(
            id = "flowcard",
            name = "FlowCard",
            developer = "Flow Education",
            category = Category.EDUCATION,
            ageRating = 0,
            size = 29.4f,
            iconUrl = "android.resource://com.vurvaa/${R.drawable.ic_app_cards}",
            screenshotUrlList = emptyList(),
            description = "Учите новое с помощью карточек. Создавайте наборы слов " +
                "и понятий, проверяйте себя и повторяйте сложные темы. Короткие " +
                "занятия удобно включать в ежедневное расписание.",
        ),
    )

    val showcasePages: List<List<AppDetails>> = apps.chunked(APPS_PER_PAGE)

    fun findApp(id: String?): AppDetails? = apps.find { it.id == id }
}
