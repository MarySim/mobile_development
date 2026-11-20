# Лабораторная работа №4. Создание списков

**Дисциплина** — «Разработка мобильных приложений»

**Выполнила** — Симонова Марина Евгеньевна, ФИТ-241, КемГУ

## Цель работы

Изучить создание прокручиваемых списков и сеток в Jetpack Compose.

## Проекты

- **Affirmations** — практическое задание. Вертикальный список из 10 карточек с фотографиями и утверждениями. Данные представлены моделью `Affirmation`, карточки отображаются в `LazyColumn`.
- **Topics** — контрольное задание. Сетка из 14 тематических карточек в два столбца. В каждой карточке показаны фотография, название темы и количество курсов. Сетка реализована через `LazyVerticalGrid`.

## Запуск

Откройте папку `Affirmations` или `Topics` как отдельный проект в Android Studio. После синхронизации Gradle выберите эмулятор или устройство и запустите конфигурацию `app`. Оба проекта используют Kotlin DSL и поддерживают Android API 24 и выше.

## Источники ресурсов

- Тексты, заготовка источника данных и фотографии для Affirmations взяты из [стартового проекта Google Developer Training](https://github.com/google-developer-training/basic-android-kotlin-compose-training-affirmations/tree/starter).
- Фотографии для Topics подобраны из [набора изображений Google Developer Training](https://github.com/google-developer-training/basic-android-kotlin-compose-training-courses/blob/main/topics.zip). Названия тем и количество курсов взяты из листингов 5.33–5.34 методички.
- Автор фотографий в обоих наборах — Romain Guy. Фотографии распространяются по [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/).
