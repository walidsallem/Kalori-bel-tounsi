package com.kalori.tounsi.ui.screens.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kalori.tounsi.data.repo.Repo
import com.kalori.tounsi.data.prefs.Prefs
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeVM(app: Application) : AndroidViewModel(app) {
    private val repo = Repo(app.applicationContext)
    private val prefs = Prefs(app.applicationContext)

    val tdee = repo.profile.map { p -> p?.let { com.kalori.tounsi.util.CalorieCalc.tdee(it) } }
    val todayCalories = repo.todayCalories()

    val goal = prefs.goal
    val mode = prefs.mode

    val waterTarget = prefs.waterTarget
    val waterToday = prefs.waterToday

    fun addWater(ml: Int) { viewModelScope.launch { prefs.addWater(ml) } }

    val suggestions = mode.map { m ->
        if (m == "fasting_16_8") listOf(
            "وجبة 1: سلطة خضرا + سمك مشوي (نحسب بالوزن النايّ) + ملاوي صغير",
            "وجبة 2: مقرونة كاملة (مطبوخة) + ياورت 0% + حفنة فواكه مجففة (20غ)"
        ) else listOf(
            "فطور: كافي أو ليه + بسيسة 30غ + ياورت 0%",
            "غداء: كسكسي خضرة + سلطة مشوية من غير زيت",
            "عشاء: أملات + سلطة تونسية + لبن"
        )
    }
}
