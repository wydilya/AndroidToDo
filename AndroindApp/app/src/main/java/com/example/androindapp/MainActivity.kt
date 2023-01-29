package com.example.androindapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androindapp.ui.theme.AndroindAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    var item by remember {
        mutableStateOf("")
    }

    var items = mutableListOf<String>()

    AndroindAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column {
                Text(
                    text = "To Do List",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )

                Row() {
                    OutlinedTextField(
                        value = item,
                        onValueChange = { item = it },
                        label = {
                            Text(text = "New Item")
                        }
                    )

                    Button(onClick = {
                        if (!item.isEmpty()) {
                            items.add(item)
                            item = ""
                        }
                    }, modifier = Modifier.padding(14.dp)) {
                        Text("Save")
                    }
                }

                Divider()

                Column() {
                    for (item in items) {
                        ToDoListItem(name = item)
                    }
                }
            }
        }
    }
}

@Composable
fun ToDoListItem(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }

    var color = MaterialTheme.colors.background
    if (isSelected) {
        color = MaterialTheme.colors.primary
    }

    Surface(
        color = color,
        modifier = Modifier.clickable {
            isSelected = !isSelected
        }
    ) {
        Text(text = name, modifier = Modifier.padding(12.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}