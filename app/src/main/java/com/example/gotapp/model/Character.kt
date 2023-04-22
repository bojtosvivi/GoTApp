package com.example.gotapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.gotapp.persistence.Converters
import kotlinx.serialization.*

typealias Characters = List<Character>

@Serializable
@Entity
@TypeConverters(Converters::class)
data class Character (
    @PrimaryKey val name: String,
    val slug: String,
    val house: House? = null,
    val quotes: List<String>
)
