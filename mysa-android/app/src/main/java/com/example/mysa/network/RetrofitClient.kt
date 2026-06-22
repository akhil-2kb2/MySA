package com.example.mysa.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class ChatRequest(val message: String)
data class ChatResponse(val response: String)

interface ChatApiService {
    @POST("/api/assistant/ask")
    suspend fun askAssistant(@Body request: ChatRequest): ChatResponse
}

object RetrofitClient {
    // 10.0.2.2 is the special alias to your host loopback interface (127.0.0.1) on the Android Emulator
    private const val BASE_URL = "http://10.0.2.2:8080"

    val apiService: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApiService::class.java)
    }
}
