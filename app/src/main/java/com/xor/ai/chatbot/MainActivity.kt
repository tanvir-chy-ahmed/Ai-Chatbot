package com.xor.ai.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.xor.ai.chatbot.ui.screens.ChatPage
import com.xor.ai.chatbot.ui.theme.AiChatbotTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            AiChatbotTheme {

                ModalNavigationDrawer(
                    drawerState = drawerState,

                    drawerContent = {
                        ModalDrawerSheet(modifier = Modifier.width(280.dp)) {
                            Spacer(modifier = Modifier.height(18.dp))
                            Text(

                                text = "Xorin Ai",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                label = {
                                    Row() {
                                        Icon(
                                            imageVector = Icons.Default.Create,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.padding(start = 15.dp))
                                        Text("New chat",)
                                    }

                                },
                                selected = true,
                                onClick = {

                                }
                            )

                            Spacer(modifier = Modifier.height(18.dp))


                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                label = {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.padding(start = 15.dp))
                                        Text("Search chat")
                                    }
                                },

                                selected = true,
                                onClick = {}
                            )

                            Spacer(modifier = Modifier.height(18.dp))


                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                label = {
                                    Row() {
                                        Icon(
                                            imageVector = Icons.Default.ImageSearch,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.padding(start = 15.dp))
                                        Text("Images")
                                    }
                                },
                                selected = true,
                                onClick = {}
                            )


                            Spacer(modifier = Modifier.height(18.dp))

                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                label = {
                                    Row() {
                                        Icon(
                                            imageVector = Icons.Default.Apps,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.padding(start = 15.dp))
                                        Text("Apps")
                                    }

                                },
                                selected = true,
                                onClick = {}
                            )


                            Spacer(modifier = Modifier.height(18.dp))

                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                                label = {
                                    Row() {
                                        Icon(
                                            imageVector = Icons.Default.AllInclusive,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.padding(start = 15.dp))
                                        Text("Projects")
                                    }
                                },

                                selected = true,
                                onClick = {}
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 15.dp)
                            ) {

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .clickable {
                                            // profile button logic goes here
                                        }
                                        .size(60.dp)
                                        .background(
                                            color = Color.Gray,
                                            shape = RoundedCornerShape(30.dp)
                                        )
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )

                                }


                                Spacer(modifier = Modifier.width(10.dp))


                                Text(
                                    text = "Skill Developer",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )


                            }


                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color(
                                        0xFFececec
                                    )
                                ),
                                title = {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .background(
                                                color = Color.White,
                                                shape = RoundedCornerShape(25.dp)
                                            )
                                            .size(50.dp)
                                    )
                                    {
                                        IconButton(onClick = {
                                            scope.launch {
                                                if (drawerState.isClosed) {
                                                    drawerState.open()
                                                } else {
                                                    drawerState.close()
                                                }

                                            }

                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = null
                                            )
                                        }
                                    }


                                },
                                actions = {
                                    Box(

                                        modifier = Modifier
                                            .height(50.dp)
                                            .fillMaxWidth(0.3f)
                                            .background(
                                                color = Color.White,
                                                shape = RoundedCornerShape(25.dp)
                                            )

                                    )
                                    {
                                        Row(
                                            modifier = Modifier.fillMaxWidth().padding(end = 12.dp, start = 12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) { IconButton(onClick = {}) {
                                            Icon(
                                                imageVector = Icons.Default.PersonAddAlt1,
                                                contentDescription = null
                                            )
                                        }
                                            Spacer(Modifier.width(10.dp))

                                            IconButton(onClick = {}) {
                                                Icon(
                                                    imageVector = Icons.Default.AreaChart,
                                                    contentDescription = null
                                                )
                                            } }

                                    }
                                }
                            )
                        }
//
                    ) { innerPadding ->

                        ChatPage(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = chatViewModel
                        )
                    }
                }
            }
        }
    }
}



