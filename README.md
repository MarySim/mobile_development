# Лабораторная работа №6. Жизненный цикл. Архитектурные компоненты

**Дисциплина** — «Разработка мобильных приложений»

**Выполнила** — Симонова Марина Евгеньевна, ФИТ-241

## Цель работы

Изучить жизненный цикл Activity, управление состоянием экрана через ViewModel и однонаправленный поток данных в Jetpack Compose.

## Проекты

- **Unscramble** — игра на 10 слов. `GameViewModel` проверяет ответы, начисляет очки, обрабатывает пропуск и запускает новый раунд. Экран подписывается на `StateFlow` состояния игры. Пять модульных тестов проверяют первый запуск, правильный и неверный ответы, пропуск и завершение игры.
- **ActivityLifecycle** — приложение для наблюдения за жизненным циклом. В Logcat по тегу `ActivityLifecycle` видны методы и идентификатор экземпляра Activity.
- **ArtSpaceApp** — галерея из ЛР №5. Выбор произведения и переключение переданы в `ArtSpaceViewModel`. Выбранная работа сохраняется при повороте экрана.
- **DessertClicker** — счётчик продаж. `DessertViewModel` хранит число десертов, выручку и текущий десерт. При повороте экрана данные продаж сохраняются.

## Запуск и проверка

Откройте папку выбранного проекта в Android Studio, дождитесь синхронизации Gradle и запустите конфигурацию `app`. Все проекты используют Kotlin DSL и поддерживают Android API 24 и выше.

Для покрытия `GameViewModel` запустите `./gradlew :app:createDebugUnitTestCoverageReport` из папки `Unscramble`. Отчёт доступен в `app/build/reports/coverage/test/debug/index.html`.

## Наблюдения в ActivityLifecycle

Ниже порядок вызовов, записанный на эмуляторе Pixel 8 Pro.

| Действие | Вызовы |
| --- | --- |
| Запуск | onCreate → onStart → onResume |
| Сворачивание через Home | onPause → onStop |
| Возврат в приложение | onRestart → onStart → onResume |
| Поворот экрана | onPause → onStop → onDestroy → onCreate → onStart → onResume |

При возврате после Home идентификатор Activity сохранился. При повороте Android создал другой экземпляр.

## Источники ресурсов

- Стартовые проекты Google Developer Training. [Unscramble](https://github.com/google-developer-training/basic-android-kotlin-compose-training-unscramble/tree/starter), [Dessert Clicker](https://github.com/google-developer-training/basic-android-kotlin-compose-training-dessert-clicker/tree/starter).
- Репродукции ArtSpaceApp из открытой коллекции Метрополитен-музея. [Большая волна в Канагаве](https://www.metmuseum.org/art/collection/search/45434), [Автопортрет в соломенной шляпе](https://www.metmuseum.org/art/collection/search/436532), [Пшеничное поле с кипарисами](https://www.metmuseum.org/art/collection/search/436535).
