package com.example.gotapp.persistence
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.GoTCharacters
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM character")
    fun getCharactersFlow(): Flow<GoTCharacters>

    @Query("SELECT * FROM character")
    fun getCharacters(): GoTCharacters

    @Query("SELECT * FROM character WHERE name = :name")
    fun findByName(name: String): GoTCharacter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(character: GoTCharacters)

    @Delete
    fun delete(character: GoTCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: GoTCharacter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(character: GoTCharacter)
}
