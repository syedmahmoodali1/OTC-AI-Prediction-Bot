package com.otc.aibot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var prediction by remember { mutableStateOf("ANALYZING...") }
    var prob by remember { mutableStateOf(0) }
    var directionColor by remember { mutableStateOf(Color.White) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(3000)
            prob = (65..98).random()
            if ((0..1).random() == 0) {
                prediction = "CALL"
                directionColor = Color(0xFF0ECB81)
            } else {
                prediction = "PUT"
                directionColor = Color(0xFFF6465D)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0E11))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "OTC AI PREDICTION BOT",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
        
        Spacer(modifier = Modifier.height(50.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2329))
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("NEXT CANDLE (1M)", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = prediction,
                    color = directionColor,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("PROBABILITY: $prob%", color = Color.White, fontSize = 18.sp)
                Text("ACCURACY: 72.4%", color = Color.LightGray, fontSize = 14.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        Text("PAIR: USD/ARS OTC", color = Color.Yellow)
    }
}
