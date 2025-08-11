package com.kalori.tounsi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kalori.tounsi.ui.screens.vm.OnboardingVM

@Composable
fun OnboardingScreen(onDone: () -> Unit) {
    val vm: OnboardingVM = viewModel()
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("شنوّة الهدف؟", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = state.goal == "lose", onClick = { vm.setGoal("lose") }, label = { Text("نحب نقصّر شحمة") })
            FilterChip(selected = state.goal == "maintain", onClick = { vm.setGoal("maintain") }, label = { Text("نثبت الميزان") })
            FilterChip(selected = state.goal == "gain", onClick = { vm.setGoal("gain") }, label = { Text("نزيد وزن") })
        }

        Text("طريقة الماكلة", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = { vm.setMode("fasting_16_8") }, label = { Text("صيام 16/8 (2 وجبات)") })
            AssistChip(onClick = { vm.setMode("normal_3") }, label = { Text("عادي (3 وجبات)") })
        }

        Text("هدف الماء في النهار (مل)", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(value = state.waterTarget, onValueChange = vm::setWaterTarget, label = { Text("مثال 2000") })

        Button(onClick = { vm.finish(onDone) }) { Text("كمّل") }
        Text("ينجّم تبدّلهم من البروفيل أي وقت.")
    }
}
