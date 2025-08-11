package com.kalori.tounsi.ui.screens.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kalori.tounsi.data.Food
import com.kalori.tounsi.data.repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddVM(app: Application) : AndroidViewModel(app) {
    private val repo = Repo(app.applicationContext)
    val query = MutableStateFlow("")
    val results = query.flatMapLatest { q -> if (q.isBlank()) repo.foods else repo.searchFoods(q) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateQuery(q: String) { query.value = q }

    fun add(food: Food, qty: Float) {
        viewModelScope.launch { repo.addMeal(food, qty) }
    }
}
