package com.example.gotapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.gotapp.persistence.Converters
import kotlinx.serialization.*

typealias GoTCharacters = List<GoTCharacter>

@Serializable
@Entity(tableName = "character")
@TypeConverters(Converters::class)
data class GoTCharacter (
    @PrimaryKey val name: String,
    val slug: String,
    val house: House? = null,
    val quotes: List<String>
)
