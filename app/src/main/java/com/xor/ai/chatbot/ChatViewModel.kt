package com.xor.ai.chatbot

import android.R.attr.text
import android.R.id.content
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.xor.ai.chatbot.domain.model.MessageModel
import com.xor.ai.chatbot.util.Constants
import kotlinx.coroutines.launch
class ChatViewModel : ViewModel() {

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-3-flash-preview", // Stable mobile SDK model
        apiKey = Constants.apiKey
    )







    fun sendMessage(question: String) {
        viewModelScope.launch {
            val chat = generativeModel.startChat(
                history = messageList.map {
                    content(it.role) { text(it.message) }
                }
            )

            // 1️⃣ Add user message first
            messageList.add(MessageModel(question, "user"))

            // 2️⃣ Add typing indicator after user message
            val typingMessage = MessageModel(
                message = "Typing...",
                role = "model",
                isTyping = true
            )
            messageList.add(typingMessage)

            try {
                val response = chat.sendMessage(question)

                // 3️⃣ Remove typing indicator
                messageList.removeAll { it.isTyping }

                // 4️⃣ Add AI response at the bottom
                messageList.add(
                    MessageModel(response.text.orEmpty(), "model")
                )

                Log.d("AI_RESPONSE", response.text ?: "Empty response")
            } catch (e: Exception) {
                messageList.removeAll { it.isTyping }

                messageList.add(
                    MessageModel("Error: ${e.message}", "model")
                )

                Log.e("AI_ERROR", e.toString())
            }
        }
    }

}
