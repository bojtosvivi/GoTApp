package com.example.gotapp.persistence
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gotapp.model.Character
import com.example.gotapp.model.Characters

@Dao
interface AppDao {
    @Query("SELECT * FROM character")
    fun getAll(): Characters

    @Query("SELECT * FROM character WHERE name = :name")
    fun findByName(name: String): Character

    @Insert
    fun insertAll(vararg character: Character)

    @Delete
    fun delete(character: Character)
}
