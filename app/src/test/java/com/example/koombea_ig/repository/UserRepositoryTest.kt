package com.example.koombea_ig.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.koombea_ig.data.models.User
import com.example.koombea_ig.mock.MockUserRepository
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
class UserRepositoryTest {

    val userRepositoryTest = MockUserRepository()
    val user = User("4", "John", "jion@gmai.com", "4.jpg")

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){

    }

    @Test
    fun getAllMockedUsersTest(){
        val userList = userRepositoryTest.getAllUsers().value
        if (userList != null) {
            assertTrue(userList.isNotEmpty())
        }
    }

    @Test
    fun insertMockedUserTest() = runBlockingTest{
        userRepositoryTest.insertUser(user)
        val userList = userRepositoryTest.getAllUsers().value
        if (userList != null) {
            assertNotNull(userList.filter { it.id == "4" })
        }
    }

    @Test
    fun deleteMockedUsersTest() = runBlockingTest{
        userRepositoryTest.deleteUser(user)
        val userList = userRepositoryTest.getAllUsers().value
        if (userList != null) {
            assertTrue(userList.filter { it.id == "4" }.isEmpty())
        }
    }

    @Test
    fun deleteAllMockedUsersTest() = runBlockingTest{
        userRepositoryTest.deleteAll()
        val userList = userRepositoryTest.getAllUsers().value
        if (userList != null) {
            assertTrue(userList.isEmpty())
        }
    }
}