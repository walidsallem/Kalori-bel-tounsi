package com.kalori.tounsi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalori.tounsi.ui.screens.vm.AddVM

@Composable
fun AddFoodScreen() {
    val vm: AddVM = viewModel()
    val query by vm.query.collectAsState()
    val foods by vm.results.collectAsState(initial = emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = vm::updateQuery,
            label = { Text("قلّب على وجبة (تونسية/عالمية)") },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(foods) { f ->
                ElevatedCard {
                Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(id = f.imageRes), contentDescription = f.name, modifier = Modifier.size(56.dp))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(f.name, style = MaterialTheme.typography.titleMedium)
                        Text("حصّة: ${f.servingDesc} | ${f.calories} كالوري")
                        var qty by remember { mutableStateOf(1f) }
                        Row {
                            Slider(value = qty, onValueChange = { qty = it }, steps = 3, valueRange = 0.5f..3f, modifier = Modifier.weight(1f))
                            Text(String.format("x %.1f", qty))
                        }
                        Button(onClick = { vm.add(f, qty) }) { Text("ضيف") }
                    }
                }
                    }
                }
            }
        }
    }
}
