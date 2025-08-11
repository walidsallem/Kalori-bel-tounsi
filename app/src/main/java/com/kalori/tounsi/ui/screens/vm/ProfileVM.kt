package com.kalori.tounsi.ui.screens.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kalori.tounsi.data.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileState(
    val gender: String = "m",
    val age: String = "25",
    val height: String = "175",
    val weight: String = "75",
    val activity: String = "med",
    val tdeeHint: Int = 0
)

class ProfileVM(app: Application) : AndroidViewModel(app) {
    val state = MutableStateFlow(ProfileState())

    fun setGender(g: String) = state.update { it.copy(gender = g) }
    fun setAge(v: String) = state.update { it.copy(age = v.filter { c -> c.isDigit() }) }
    fun setHeight(v: String) = state.update { it.copy(height = v.filter { c -> c.isDigit() }) }
    fun setWeight(v: String) = state.update { it.copy(weight = v.filter { c -> c.isDigit() || c == '.' }) }
    fun setActivity(a: String) = state.update { it.copy(activity = a) }

    fun save() {
        viewModelScope.launch {
            val s = state.value
            val profile = UserProfile(
                gender = s.gender,
                age = s.age.toIntOrNull() ?: 25,
                heightCm = s.height.toIntOrNull() ?: 175,
                weightKg = s.weight.toFloatOrNull() ?: 75f,
                activity = s.activity
            )
            val tdee = com.kalori.tounsi.util.CalorieCalc.tdee(profile)
            state.update { it.copy(tdeeHint = tdee) }
        }
    }
}
