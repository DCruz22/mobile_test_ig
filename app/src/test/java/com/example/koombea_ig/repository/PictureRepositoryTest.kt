package com.example.koombea_ig.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.mock.MockPictureRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.*

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PictureRepositoryTest {

    val pictureRepositoryTest = MockPictureRepository()
    val picture = Picture(10, "j.jpg", 2)

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){

    }

    @Test
    fun getAllMockedPicturesTest(){
        val pictureList = pictureRepositoryTest.getAllPicture().value
        if (pictureList != null) {
            assertTrue(pictureList.isNotEmpty())
        }
    }

    @Test
    fun insertMockedPictureTest() = runBlockingTest{
        pictureRepositoryTest.insertPicture(picture)
        val pictureList = pictureRepositoryTest.getAllPicture().value
        if (pictureList != null) {
            assertNotNull(pictureList.filter { it.id == 10 })
        }
    }

    @Test
    fun deleteMockedPicturesTest() = runBlockingTest{
        pictureRepositoryTest.deletePicture(picture)
        val pictureList = pictureRepositoryTest.getAllPicture().value
        if (pictureList != null) {
            assertTrue(pictureList.filter { it.id == 10 }.isEmpty())
        }
    }

    @Test
    fun deleteAllMockedPicturesTest() = runBlockingTest{
        pictureRepositoryTest.deleteAll()
        val pictureList = pictureRepositoryTest.getAllPicture().value
        if (pictureList != null) {
            assertTrue(pictureList.isEmpty())
        }
    }
}