package com.example.a5term

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    // Счётчик выпитой воды за текущий день
    var waterCount by remember { mutableIntStateOf(0) }
    // Счётчик дней подряд, когда выпито >= 1500 мл
    var daysStreak by remember { mutableIntStateOf(0) }

    // Цвета (можно заменить на свои)
    val primaryColor = Color(0xFF3F51B5)
    val secondaryColor = Color(0xFF455A64)
    val buttonTextColor = Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка добавления воды
        Button(
            onClick = { waterCount += 250 }, // Прибавляем 250 мл
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка завершения дня
        Button(
            onClick = {
                if (waterCount >= 1500) {
                    daysStreak += 1 // Норма выполнена, увеличиваем счетчик дней
                } else {
                    daysStreak = 0 // Норма не выполнена, сбрасываем
                }
                waterCount = 0 // Сбрасываем воду на новый день
            },
            colors = ButtonDefaults.buttonColors(containerColor = secondaryColor)
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Вывод количества дней подряд
        Text(
            text = "Дней подряд с нормой 1500 мл: $daysStreak",
            fontSize = 18.sp,
            color = primaryColor
        )
    }
}

// Превью для проверки в Android Studio
@Preview(name = "8. Water Tracker", widthDp = 411, heightDp = 891, showBackground = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}