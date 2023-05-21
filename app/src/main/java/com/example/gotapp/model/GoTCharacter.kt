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
) {
    fun getImage(): String {
        return when (slug) {
            "jon" -> "https://media.gq-magazine.co.uk/photos/62ac38f82da9f5f89888eba9/1:1/w_667,h_667,c_limit/jon-snow-series-1200.jpeg"
            "sansa" -> "https://hips.hearstapps.com/elle/assets/17/32/1502282689-e68346e4a713a0a9558be85255557819650b72a0e3f7498aebcaf70319f541cffe081a3669fb29e8742a54577bd535f9.jpg"
            "tyrion" -> "https://hips.hearstapps.com/hmg-prod/images/theory-1553634761.jpg"
            "jaime" -> "https://hips.hearstapps.com/hmg-prod/images/jaime-game-of-thrones-1551987282.jpg"
            "ned" -> "https://static.wikia.nocookie.net/gameofthrones/images/3/34/Eddard_Stark.jpg"
            else -> ""
        }
    }
}
