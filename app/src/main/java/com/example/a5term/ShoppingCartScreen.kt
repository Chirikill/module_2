package com.example.a5term

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

// Модели данных из задания
data class Cart(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

@Composable
fun ShoppingCartScreen() {
    // Начальное состояние корзины (по условию задачи)
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }

    // Считаем сумму и количество динамически при каждой перерисовке
    val totalSum = products.sumOf { it.price }
    val productSize = products.size

    // Следим за суммой. Если она стала больше 500, показываем Toast ОДИН раз
    val context = LocalContext.current
    LaunchedEffect(totalSum) {
        if (totalSum > 500) {
            Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Выводим список товаров
        for (product in products) {
            Text(text = "${product.name} - ${product.price} рублей")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Выводим общую сумму
        Text(text = "Товаров на сумму: $totalSum рублей")

        Spacer(modifier = Modifier.height(16.dp))

        // Блок добавления товара
        AddProductSection {
            // Создаем новый товар со случайной ценой от 100 до 500
            val newProduct = Product(
                id = products.size,
                name = "Товар #${products.size + 1}",
                price = Random.nextInt(100, 500)
            )
            // Обновляем список (создаем новый список, а не мутируем старый)
            products = products + newProduct
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Блок удаления товара
        RemoveProductSection(hasProducts = productSize > 0) {
            products = products.dropLast(1) // удаляем последний
        }
    }
}

@Composable
fun AddProductSection(onAdd: () -> Unit) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(hasProducts: Boolean, onRemove: () -> Unit) {
    // Кнопка удаления показывается только если товары есть
    if (hasProducts) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}

// Превью для проверки в Android Studio
@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    ShoppingCartScreen()
}