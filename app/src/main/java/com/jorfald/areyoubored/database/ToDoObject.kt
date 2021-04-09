package com.jorfald.areyoubored.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do")
data class ToDoObject(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    @PrimaryKey
    val key: String,
    val accessibility: Double
)