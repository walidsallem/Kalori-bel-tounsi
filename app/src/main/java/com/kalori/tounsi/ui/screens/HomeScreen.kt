package com.kalori.tounsi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalori.tounsi.ui.screens.vm.HomeVM

@Composable
fun HomeScreen(nav: androidx.navigation.NavHostController) {
    val vm: HomeVM = viewModel()
    val tdee by vm.tdee.collectAsState(initial = null)
    val today by vm.todayCalories.collectAsState(initial = 0)
    val waterTarget by vm.waterTarget.collectAsState(initial = 2000)
    val waterToday by vm.waterToday.collectAsState(initial = 0)
    val suggestions by vm.suggestions.collectAsState(initial = emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("شنوّة كلّيت اليوم؟", style = MaterialTheme.typography.headlineSmall)
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("المجموع اليومي: $today كالوري")
                Text("الحدّ (اليوم): ${tdee ?: "كمّل البروفيل"}")
                if (tdee != null) {
                    val rest = (tdee!! - today).coerceAtLeast(0)
                    LinearProgressIndicator(progress = (today.toFloat() / tdee!!.toFloat()).coerceIn(0f,1f))
                    Text("بقيتلك تقريبًا: $rest كالوري")
                } else {
                    Text("كمّل معلوماتك في البروفيل")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { nav.navigate("add") }) { Text("زيد وجبة") }
                    Button(onClick = { nav.navigate("history") }) { Text("تاريخ") }
                }
            }
        }

        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("الماء", style = MaterialTheme.typography.titleMedium)
                LinearProgressIndicator(progress = (waterToday.toFloat() / waterTarget.toFloat()).coerceIn(0f,1f))
                Text("شربت: ${waterToday}مل / الهدف: ${waterTarget}مل")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { vm.addWater(250) }) { Text("+250مل") }
                    Button(onClick = { vm.addWater(500) }) { Text("+500مل") }
                }
                Text("ما تنساش تشرب الماء خلال النهار 💧")
            }
        }

        Text("اقتراحات وجبات", style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(suggestions) { s ->
                ElevatedCard { Text(s, Modifier.padding(12.dp)) }
            }
        }
    }
}
