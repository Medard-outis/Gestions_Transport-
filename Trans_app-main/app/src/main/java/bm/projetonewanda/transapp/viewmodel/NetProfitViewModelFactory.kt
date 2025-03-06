package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bm.projetonewanda.transapp.data.repository.NetProfitRepository

class NetProfitViewModelFactory(private val repository: NetProfitRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetProfitViewModel::class.java)) {
            return NetProfitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
