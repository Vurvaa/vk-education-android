package com.vurvaa

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vurvaa.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Работа с Intent",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Текст или номер телефона") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val text = inputText.trim()
                    if (text.isEmpty()) {
                        context.showToast("Введите текст для второй Activity")
                        return@Button
                    }

                    val intent = Intent(context, SecondActivity::class.java).apply {
                        putExtra(SecondActivity.EXTRA_TEXT, text)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Открыть вторую Activity")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    val phone = inputText.trim()
                    if (phone.isEmpty()) {
                        context.showToast("Введите номер телефона")
                        return@Button
                    }
                    if (!phone.isDialablePhoneNumber()) {
                        context.showToast("Введите корректный номер телефона")
                        return@Button
                    }

                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.fromParts("tel", phone, null)
                    }

                    try {
                        context.startActivity(intent)
                    } catch (_: ActivityNotFoundException) {
                        context.showToast("Приложение для звонков не найдено")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Позвонить другу")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    val text = inputText.trim()
                    if (text.isEmpty()) {
                        context.showToast("Введите текст для отправки")
                        return@Button
                    }

                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }
                    val chooser = Intent.createChooser(sendIntent, "Поделиться через...")

                    try {
                        context.startActivity(chooser)
                    } catch (_: ActivityNotFoundException) {
                        context.showToast("Нет приложения для отправки текста")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Поделиться текстом")
            }
        }
    }
}

private fun android.content.Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

private fun String.isDialablePhoneNumber(): Boolean {
    val hasDigit = any { it.isDigit() }
    val hasOnlyDialCharacters = all { it.isDigit() || it in setOf('+', ' ', '-', '(', ')') }
    return hasDigit && hasOnlyDialCharacters
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    FirstAppTheme {
        MainScreen()
    }
}
