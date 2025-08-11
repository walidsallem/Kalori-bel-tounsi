package com.kalori.tounsi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalori.tounsi.ui.screens.vm.ProfileVM

@Composable
fun ProfileScreen() {
    val vm: ProfileVM = viewModel()
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("بروفيل", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = state.gender == "m", onClick = { vm.setGender("m") }, label = { Text("راجل") })
            FilterChip(selected = state.gender == "f", onClick = { vm.setGender("f") }, label = { Text("مرة") })
        }
        OutlinedTextField(value = state.age, onValueChange = vm::setAge, label = { Text("العمر") })
        OutlinedTextField(value = state.height, onValueChange = vm::setHeight, label = { Text("الطول (سم)") })
        OutlinedTextField(value = state.weight, onValueChange = vm::setWeight, label = { Text("الوزن (كغ)") })
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = { vm.setActivity("low") }, label = { Text("قليل حركة") })
            AssistChip(onClick = { vm.setActivity("med") }, label = { Text("متوسط") })
            AssistChip(onClick = { vm.setActivity("high") }, label = { Text("برشا") })
        }
        Button(onClick = vm::save) { Text("سجّل") }
        Text("الكالوري اليومية: ${state.tdeeHint}")
    }
}
