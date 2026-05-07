package com.il.apptransporte.data.model

data class AppNotification(
    val title: String,
    val message: String,
    val time: String,
    val isRead: Boolean = false
)