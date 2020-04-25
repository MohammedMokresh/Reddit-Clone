package com.mokresh.redditclone.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mokresh.redditclone.app.ApiServices
import com.mokresh.redditclone.data.mockWebServer.MockWebServerRule
import com.mokresh.redditclone.di.TestKoinModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject


/**
 * This test are for testing the ApiService
 * **/
class ApiServiceTest : AutoCloseKoinTest() {

    @get:Rule
    var testRule = InstantTaskExecutorRule()

    @get:Rule
    var mockWebServer = MockWebServerRule()

    private val service: ApiServices by inject()

    @Before
    fun before() {
        startKoin {
            modules(TestKoinModules.getModules())
        }
    }

    @Test
    fun listingTest() {
        service.listing()
            .test()
            .await()
            .assertValue { it.size == 20 }
    }


}
