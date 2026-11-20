package com.example.topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @param:StringRes val nameResourceId: Int,
    val courseCount: Int,
    @param:DrawableRes val imageResourceId: Int
)
