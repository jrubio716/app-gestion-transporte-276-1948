package com.il.apptransporte.data.model

data class User(
    val fullName: String,
    val role: String,
    val dni: String? = null,
    val company: String? = null,
    val email: String,
    val phone: String,
    val license: String? = null
)