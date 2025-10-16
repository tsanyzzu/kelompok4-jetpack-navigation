package com.example.composenavigationapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Item(val id: String, val title: String)

class ItemsViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(listOf(
        Item("A01", "Judul Item 1"),
        Item("A02", "Judul Item 2"),
        Item("A03", "Judul Item 3"),
        Item("A04", "Judul Item 4"),
        Item("A05", "Judul Item 5"),
    ))
    val items: StateFlow<List<Item>> = _items

    fun addItem(id: String, title: String) {
        val newList = _items.value.toMutableList().apply { add(Item(id, title)) }
        _items.value = newList
    }
}


