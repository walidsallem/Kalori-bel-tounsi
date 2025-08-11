package com.kalori.tounsi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalori.tounsi.ui.screens.vm.HistoryVM

@Composable
fun HistoryScreen() {
    val vm: HistoryVM = viewModel()
    val itemsList by vm.meals.collectAsState(initial = emptyList())
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("شنوّا كلّيت قبل؟", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(itemsList) { item ->
                ElevatedCard {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("كالوري: ${item.totalCalories}")
                        Text("كمية: ${item.quantity} من الحصّة")
                        Button(onClick = { vm.delete(item.id) }) { Text("مسح") }
                    }
                }
            }
        }
        Button(onClick = { vm.clear() }) { Text("مسح الكل") }
    }
}
