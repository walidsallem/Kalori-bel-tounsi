package com.kalori.tounsi.ui.screens.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kalori.tounsi.data.repo.Repo
import kotlinx.coroutines.launch

class HistoryVM(app: Application) : AndroidViewModel(app) {
    private val repo = Repo(app.applicationContext)
    val meals = repo.meals

    fun delete(id: Int) { viewModelScope.launch { repo.deleteMeal(id) } }
    fun clear() { viewModelScope.launch { repo.clearAll() } }
}
