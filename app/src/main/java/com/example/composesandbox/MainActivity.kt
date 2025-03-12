package com.example.composesandbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesandbox.components.FadedText
import com.example.composesandbox.components.TruncationDirection
import com.example.composesandbox.ui.theme.ComposeSandboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeSandboxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Spacer(modifier = Modifier.padding(innerPadding))
                        Spacer(Modifier.fillMaxWidth().height(20.dp))
                        Text("LTR Start Fade")
                        FadedText(
                            text = "somesubdomain.google.com.roguewebsite.com",
                            modifier = Modifier.width(200.dp).border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = AbsoluteCutCornerShape(1.dp),
                            ),
                            style = TextStyle(fontSize = 16.sp),
                            truncationDirection = TruncationDirection.START,
                            fadeLength = 100.dp,
                        )
                        Spacer(Modifier.fillMaxWidth().height(20.dp))
                        Text("LTR End Fade")
                        FadedText(
                            text = "somesubdomain.google.com.roguewebsite.com",
                            modifier = Modifier.width(200.dp).border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = AbsoluteCutCornerShape(1.dp),
                            ),
                            style = TextStyle(fontSize = 16.sp),
                            truncationDirection = TruncationDirection.END,
                            fadeLength = 50.dp,
                        )
                        Spacer(Modifier.fillMaxWidth().height(20.dp))
                        Text("Regular Text box LTR")
                        Text(
                            text = "somesubdomain.google.co",
                            modifier = Modifier.width(200.dp).border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = AbsoluteCutCornerShape(1.dp),
                            ),
                            style = TextStyle(fontSize = 16.sp),
                        )
                    }
                }
            }
        }
    }
}