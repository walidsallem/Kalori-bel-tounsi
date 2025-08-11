package com.kalori.tounsi.ui.screens.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kalori.tounsi.data.prefs.Prefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OnboardingState(
    val goal: String = "maintain",
    val mode: String = "normal_3",
    val waterTarget: String = "2000"
)

class OnboardingVM(app: Application) : AndroidViewModel(app) {
    private val prefs = Prefs(app.applicationContext)
    val state = MutableStateFlow(OnboardingState())

    fun setGoal(g: String) = state.update { it.copy(goal = g) }
    fun setMode(m: String) = state.update { it.copy(mode = m) }
    fun setWaterTarget(v: String) = state.update { it.copy(waterTarget = v.filter { c -> c.isDigit() }) }

    fun finish(done: () -> Unit) {
        viewModelScope.launch {
            val s = state.value
            prefs.setGoal(s.goal)
            prefs.setMode(s.mode)
            prefs.setWaterTarget(s.waterTarget.toIntOrNull() ?: 2000)
            prefs.setFirstRun(false)
            done()
        }
    }
}
