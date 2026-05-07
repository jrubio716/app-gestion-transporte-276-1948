package com.il.apptransporte.data.model

data class Evidence(
    val transportOrderCode: String,
    val dateTime: String,
    val observation: String = ""
)