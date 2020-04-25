package com.mokresh.redditclone.data.mockWebServer

import android.util.Log
import com.mokresh.redditclone.utils.Constants
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import okhttp3.mockwebserver.MockWebServer
import java.io.IOException
import java.lang.Exception


class MockWebServerRule : TestRule {

    companion object {
        const val MOCK_WEBSERVER_PORT = 8000
        const val MOCK_WEB_SERVER_URL = Constants.API_URL
    }

    private lateinit var mServer: MockWebServer

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                startServer()
                try {
                    base?.evaluate()
                } finally {
                    stopServer()
                }
            }
        }
    }


    fun server(): MockWebServer {
        return mServer
    }

    fun startServer() {
        mServer = MockWebServer()
        try {
            mServer.start(MOCK_WEBSERVER_PORT)
            mServer.dispatcher =
                DispatcherMockWebServer()
        } catch (e: Exception) {
            Log.e( "mock server start issue",e.message)
        }

    }

    fun stopServer() {
        try {
            mServer.shutdown();
        } catch (e: IOException) {
            Log.e( "mock server shutdown error",e.message)
        }
    }


}