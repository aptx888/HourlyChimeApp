package com.example.idolalarm.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.idolalarm.service.ChimeService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            IdolAlarmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        onStartClicked = {
                            startService(Intent(this, ChimeService::class.java))
                        },
                        onStopClicked = {
                            stopService(Intent(this, ChimeService::class.java))
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(onStartClicked: () -> Unit, onStopClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("偶像报时 APP")
        Button(onClick = onStartClicked, modifier = Modifier.padding(top = 16.dp)) {
            Text("启动报时")
        }
        Button(onClick = onStopClicked, modifier = Modifier.padding(top = 16.dp)) {
            Text("停止报时")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    IdolAlarmTheme {
        MainScreen({}, {})
    }
}
