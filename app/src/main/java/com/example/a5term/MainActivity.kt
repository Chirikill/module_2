package com.example.a5term

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a5term.ui.theme._5termTheme
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _5termTheme {
                // Задания 1-7:
                // Ex4()

                // Задание 8 (Трекер воды):
                // WaterTracker()ы

                // Задание 10 (Корзина товаров):
                ShoppingCartScreen()
            }
        }
    }
}

//компонуемые функции
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) { //Modifier, который передаётся её первому дочернему элементу для обеспечения гибкой настройки.
    Surface (color = Color.Magenta){
        Text( // компонуемая функция Text(), куда передаётся строковое сообщение для его отображения в пользовательском интерфейсе
            text = "Hello $name!", //именованные параметры
            modifier = modifier
        )
    }
}

@Preview(showBackground = true) //аннотация, чтобы предварительно просмотреть текст элемент
@Composable
fun GreetingPreview() {
    _5termTheme {
        Greeting("Android")
    }
}

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier){
    //обертываем, что опустить текст ниже
    Column(modifier = modifier.padding(16.dp)) {
        if (name == null) {
            Text(
                text = "Имя не задано"
            )
        } else {
            Text(
                text = "Привет, $name !"
            )
        }
    }
}

@Preview()
@Composable
fun HelloPreview() {
    Hello(name = "Анна")
}

@Composable
fun Ex3() {
    Column {
        // ВАРИАНТ 1: Зеленый, курсив, по центру, 16.sp
        Text(
            text = stringResource(id = R.string.long_text),
            color = Color.Green,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() // Нужно для выравнивания по центру
        )
        Spacer(modifier = Modifier.height(16.dp))

        // ВАРИАНТ 2: Одна строка с многоточием в конце
        Text(
            text = stringResource(id = R.string.long_text),
            maxLines = 1, // Разрешаем только 1 строку
            overflow = TextOverflow.Ellipsis // Если не влезает, то ставим "..."
        )

        // ВАРИАНТ 3: Отступ 48, размер 24, черный на зеленом, подчеркнутый
        Text(
            text = stringResource(id = R.string.long_text),
            fontSize = 24.sp,
            color = Color.Black,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 48.dp) // Отступ перед текстом
                .background(Color.Green) // Зеленый фон
        )
    }
}

@Preview
@Composable
fun Ex3Preview() {
    Ex3()
}

@Composable
fun Ex4() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp), // кнопка опуститься вниз на 50pd
        contentAlignment = Alignment.TopCenter // Выравниваем по центру
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color.Gray),
            color = Color.LightGray,
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Нажми на меня",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun Ex4Preview(){
    Ex4()
}


@Composable
fun Ex5() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxHeight() // Занимает 68dp в высоту
                .aspectRatio(1f) // <-- ДЕЛАЕТ ШИРИНУ РАВНОЙ ВЫСОТЕ (68x68)
                .clip(CircleShape)
                .background(Color(0xFFFF5722))
        ) {
            Text(
                text = "АБ",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=400dp,height=800dp")
@Composable
fun Task5Preview() {
    Ex5()
}


//6 задание
@Composable
fun CircleTopRight(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Circle",
            modifier = Modifier
                .align(Alignment.TopEnd) // правый верхний угол
        )
    }
}

@Composable
fun CircleCenterStretched(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Circle",
            modifier = Modifier
                .fillMaxSize() // Растягиваем область картинки на весь Box
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF9C27B0))
        )
    }
}

@Preview(name = "6.1 Circle TopRight (black)", widthDp = 240, heightDp = 120, showBackground = true)
@Composable
fun CircleTopRightPreview() {
    CircleTopRight()
}

@Preview(name = "6.2 Circle Center Stretched (blue)", widthDp = 240, heightDp = 120, showBackground = true)
@Composable
fun CircleCenterStretchedPreview() {
    CircleCenterStretched()
}


//7
@Composable
fun ContactColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp) // Отступ от краев экрана
    ) {
        // Одна карточка, белый фон, текст слева
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Белый фон
        ) {
            Text(text = "Имя: Евгений", color = Color.Black)
            Text(text = "Отчество: Андреевич", color = Color.Black)
            Text(text = "Фамилия: Лукашин", color = Color.Black)
            Text(text = "Мобильный телефон: +7 495 495 95 95", color = Color.Black)
            Text(text = "Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12", color = Color.Black)
        }
    }
}

@Preview(name = "7.1 Contact Column", widthDp = 411, heightDp = 891, showBackground = true)
@Composable
fun ContactColumnPreview() {
    ContactColumn()
}


@Composable
fun ContactCard(
    name: String,
    middleName: String,
    surname: String,
    phone: String,
    address: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFBDBDBD))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, textAlign = TextAlign.Center)
        Text(text = middleName, textAlign = TextAlign.Center)
        Text(text = surname, textAlign = TextAlign.Center)
        Text(text = phone, textAlign = TextAlign.Center)
        Text(text = address, textAlign = TextAlign.Center)
    }
}

@Composable
fun ContactRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            // Используем чуть более темный серый, чтобы белый текст точно было видно (если тема темная)
            .background(Color(0xFF9E9E9E))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Добавлен color = Color.Black для видимости
            Text(text = "Имя: Евгений", textAlign = TextAlign.Center, color = Color.Black)
            Text(text = "Отчество: Андреевич", textAlign = TextAlign.Center, color = Color.Black)
            Text(text = "Фамилия: Лукашин", textAlign = TextAlign.Center, color = Color.Black)
            Text(text = "Мобильный телефон: +7 495 495 95 95", textAlign = TextAlign.Center, color = Color.Black)
            Text(text = "Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12", textAlign = TextAlign.Center, color = Color.Black)
        }
        Icon(
            imageVector = Icons.Filled.Star, // Если ругается, замените на Icons.Default.Star
            contentDescription = "Избранное",
            tint = Color(0xFFFFC107) // Желтый цвет звезды
        )
    }
}

@Preview(name = "7.3 Row со звездой", widthDp = 411, heightDp = 200, showBackground = true)
@Composable
fun ContactRowPreview() {
    // Если используете свою тему, оберните в неё:
    // LearningTheme { ContactRow() }
    ContactRow()
}






