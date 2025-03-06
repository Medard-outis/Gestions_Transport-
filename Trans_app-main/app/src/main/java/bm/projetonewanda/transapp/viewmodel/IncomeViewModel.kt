package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bm.projetonewanda.transapp.data.model.Income
import bm.projetonewanda.transapp.data.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IncomeViewModel(private val incomeRepository: IncomeRepository) : ViewModel() {

    private val _incomes = MutableStateFlow<List<Income>>(emptyList())
    val incomes: StateFlow<List<Income>> = _incomes

    fun getIncomesByVehicle(vehicleId: Int) {
        viewModelScope.launch {
            incomeRepository.getIncomesByVehicle(vehicleId).collect {
                _incomes.value = it
            }
        }
    }

    fun addIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.insert(income)
        }
    }

    fun getTotalIncome(incomes: List<Income>): Double {
        return incomes.sumOf { it.amount }
    }



    fun deleteIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.delete(income)
        }
    }
    fun getAllIncomes() {
        viewModelScope.launch {
            incomeRepository.getAllIncomes().collect {
                _incomes.value = it
            }
        }
    }

}
