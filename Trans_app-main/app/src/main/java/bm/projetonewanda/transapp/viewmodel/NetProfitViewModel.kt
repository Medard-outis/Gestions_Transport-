package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bm.projetonewanda.transapp.data.repository.NetProfitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetProfitViewModel(private val repository: NetProfitRepository) : ViewModel() {

    private val _dailyProfit = MutableStateFlow(0.0)
    val dailyProfit: StateFlow<Double> = _dailyProfit

    private val _monthlyProfit = MutableStateFlow(0.0)
    val monthlyProfit: StateFlow<Double> = _monthlyProfit

    fun getDailyProfit(date: String) {
        viewModelScope.launch {
            _dailyProfit.value = repository.getDailyProfit(date)
        }
    }

    fun getMonthlyProfit(month: String) {
        viewModelScope.launch {
            _monthlyProfit.value = repository.getMonthlyProfit(month)
        }
    }
}
