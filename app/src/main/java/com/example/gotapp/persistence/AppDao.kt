package com.example.gotapp.persistence
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.GoTCharacters

@Dao
interface AppDao {
    @Query("SELECT * FROM character")
    fun getAll(): GoTCharacters

    @Query("SELECT * FROM character WHERE name = :name")
    fun findByName(name: String): GoTCharacter

    @Insert
    fun insertAll(character: GoTCharacters)

    @Delete
    fun delete(character: GoTCharacter)
}
