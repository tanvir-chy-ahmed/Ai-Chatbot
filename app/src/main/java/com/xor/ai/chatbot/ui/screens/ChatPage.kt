package com.xor.ai.chatbot.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xor.ai.chatbot.ChatViewModel
import com.xor.ai.chatbot.domain.model.MessageModel

@Composable
fun ChatPage(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFececec))
            .imePadding()
    ) {

        MessageList(
            modifier = Modifier.weight(1f),
            messageList = viewModel.messageList
        )

        MessageInput(
            onMessageSend = { viewModel.sendMessage(it) }
        )
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .focusRequester(focusRequester),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Add button
        IconButton(
            onClick = { /* Handle add action */ },
            modifier = Modifier
                .size(50.dp)
                .background(Color.White, RoundedCornerShape(25.dp))
        ) {
            Icon(Icons.Filled.Add, contentDescription = null, tint = Color.Gray)
        }

        // Text input
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Ask Xorin") },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    if (message.isNotBlank()) {
                        onMessageSend(message)
                        message = ""
                        keyboardController?.hide()
                    }
                }
            ),
            trailingIcon = {
                if (message.isNotBlank()) {
                    // Send button inside text field
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(35.dp)
                            .background(Color.Black, RoundedCornerShape(20.dp))
                            .clickable {
                                onMessageSend(message)
                                message = ""
                                keyboardController?.hide()
                            }
                    ) {
                        Icon(
                            Icons.Filled.ArrowUpward,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                } else {
                    // Mic button
                    IconButton(onClick = { /* handle mic */ }) {
                        Icon(Icons.Filled.Mic, contentDescription = null)
                    }
                }
            }
        )
    }
}

@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    messageList: List<MessageModel>
) {
    val listState = rememberLazyListState()

    if (messageList.isEmpty()) {
        // Empty placeholder
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Icon(
//                imageVector = Icons.Filled.Thunderstorm,
//                contentDescription = null,
//                tint = Color.Black,
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(10.dp)
//            )
            Text(
                "What can I help with?",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    SingleChip(
                        title = "Create image",
                        icon = Icons.Filled.Image,
                        iconColor = Color(0xFF3AA755)
                    )
                    Spacer(Modifier.width(8.dp))
                    SingleChip(
                        title = "Summarize text",
                        icon = Icons.Filled.Assignment,
                        iconColor = Color(0xFFda8250)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    SingleChip(
                        title = "Make a plan",
                        icon = Icons.Filled.Lightbulb,
                        iconColor = Color(0xFFd2c160)
                    )
                    Spacer(Modifier.width(8.dp))
                    SingleChip(title = "More")
                }
            }

        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            state = listState
        ) {
            items(messageList) { message ->
                MessageRow(messageModel = message)
            }
        }

        // Scroll to last message
        LaunchedEffect(messageList.size) {
            if (messageList.isNotEmpty()) {
                listState.animateScrollToItem(messageList.lastIndex)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = if (isModel) 8.dp else 70.dp,
                    end = if (isModel) 70.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .background(
                    color = if (isModel) Color(0xFF2E7D32) else Color(0xFF1565C0),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = messageModel.message,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        }
    }
}

@Composable
fun SingleChip(title: String, icon: ImageVector? = null, iconColor: Color? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
            .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(25.dp))
            .clickable { /* handle click */ }
            .padding(horizontal = 16.dp)
    ) {
        icon?.let {
            if (iconColor != null) {
                Icon(
                    imageVector = it,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
