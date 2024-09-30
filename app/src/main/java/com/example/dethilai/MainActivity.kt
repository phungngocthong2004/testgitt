package com.example.dethilai

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dethilai.ui.theme.DethilaiTheme
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // chuyen man hinh sau x giay
//        var abc = Intent(this, HOme::class.java)
//        var i = Timer();
//        i.schedule(object : TimerTask() {
//            override fun run() {
//                startActivity(abc)
//            }
//        }, 2000)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
                Text(text = "Hello Huynh01410")
                Button(onClick = {
                    var i = Intent(this@MainActivity, HOme::class.java)
                    startActivity(i)
                }) {
                    Text(text = "Go TO Main ")
                }
            }

        }
    }
}
