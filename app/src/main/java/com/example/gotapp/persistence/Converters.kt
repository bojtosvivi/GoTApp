package com.example.gotapp.persistence

import androidx.room.TypeConverter
import com.example.gotapp.model.House
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromHouse(value: House?): String? {
        return value?.let { Json.encodeToString(House.serializer(), it) }
    }

    @TypeConverter
    fun stringToHouse(house: String?): House? {
        return house?.let { Json.decodeFromString(House.serializer(), it) }
    }

    @TypeConverter
    fun fromQuotes(value: List<String>?): String? {
        return value?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toQuotes(quotes: String?): List<String>? {
        return quotes?.let { Json.decodeFromString(it) }
    }
}