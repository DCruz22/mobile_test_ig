package com.example.koombea_ig.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.koombea_ig.data.models.Post
import com.example.koombea_ig.mock.MockPostRepository
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
class PostRepositoryTest {

    val postRepositoryTest = MockPostRepository()
    val post = Post(5, "08/12/2026", "2")

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){

    }

    @Test
    fun getAllMockedPostsTest(){
        val postList = postRepositoryTest.getAllPosts().value
        if (postList != null) {
            assertTrue(postList.isNotEmpty())
        }
    }

    @Test
    fun insertMockedPostTest() = runBlockingTest{
        postRepositoryTest.insertPost(post)
        val postList = postRepositoryTest.getAllPosts().value
        if (postList != null) {
            assertNotNull(postList.filter { it.id == 5 })
        }
    }

    @Test
    fun deleteMockedPostsTest() = runBlockingTest{
        postRepositoryTest.deletePost(post)
        val postList = postRepositoryTest.getAllPosts().value
        if (postList != null) {
            assertTrue(postList.filter { it.id == 5 }.isEmpty())
        }
    }

    @Test
    fun deleteAllMockedPostsTest() = runBlockingTest{
        postRepositoryTest.deleteAll()
        val userList = postRepositoryTest.getAllPosts().value
        if (userList != null) {
            assertTrue(userList.isEmpty())
        }
    }
}