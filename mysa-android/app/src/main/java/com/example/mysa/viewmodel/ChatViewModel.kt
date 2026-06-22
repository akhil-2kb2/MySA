package com.example.mysa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysa.network.ChatRequest
import com.example.mysa.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Message(val text: String, val isUser: Boolean)

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        // Add user message
        _messages.value = _messages.value + Message(text, isUser = true)
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.askAssistant(ChatRequest(text))
                _messages.value = _messages.value + Message(response.response, isUser = false)
            } catch (e: Exception) {
                _messages.value = _messages.value + Message("Error: ${e.message}", isUser = false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
