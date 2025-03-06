package bm.projetonewanda.transapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bm.projetonewanda.transapp.data.model.Income
import bm.projetonewanda.transapp.viewmodel.IncomeViewModel

@Composable
fun IncomeHistoryScreen(incomeViewModel: IncomeViewModel) {
    val incomes by incomeViewModel.incomes.collectAsState()

    LaunchedEffect(Unit) {
        incomeViewModel.getAllIncomes()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Historique des Recettes", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(incomes) { income ->
                IncomeItem(income)
            }
        }
    }
}

@Composable
fun IncomeItem(income: Income) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Véhicule ID: ${income.vehicleId}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Montant: ${income.amount}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date: ${income.date}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
