package com.example.gotapp.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.gotapp.model.GoTCharacter
import com.example.gotapp.model.House
import com.example.gotapp.rules.CoroutineTestRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33],manifest=Config.NONE)
 class DatabaseTest {
  lateinit var db: AppDatabase
  private lateinit var appDao: AppDao

  @get:Rule
  val coroutinesRule = CoroutineTestRule()

  @Before
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    appDao = db.characterDao()
  }

  @After
  fun closeDB() {
    db.close()
  }


  @Test
  fun insertAllAndGetByNameTest() = runTest {
    val mockDataList = listOf(
      GoTCharacter(
      name = "Vivi",
        slug = "jon",
    house = House(slug = "test", name = "Stark"),
    quotes = listOf()
    ),
      GoTCharacter(
        name = "Bonbi",
        slug = "jon",
        house = House(slug = "test2", name = "Stark"),
        quotes = listOf()
      )
    )
    appDao.insertAll(mockDataList)

    val loadFromDB = appDao.getCharacters()
    assert(loadFromDB.size == 2)

    val mockData = appDao.findByName("Bonbi")
    assert(loadFromDB.firstOrNull { it == mockData } != null)
  }
}