package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bm.projetonewanda.transapp.data.repository.IncomeRepository

class IncomeViewModelFactory(private val incomeRepository: IncomeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IncomeViewModel::class.java)) {
            return IncomeViewModel(incomeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
