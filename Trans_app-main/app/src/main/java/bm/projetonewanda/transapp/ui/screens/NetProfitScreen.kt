package bm.projetonewanda.transapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme

import bm.projetonewanda.transapp.viewmodel.NetProfitViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NetProfitScreen(viewModel: NetProfitViewModel) {
    val dailyProfit by viewModel.dailyProfit.collectAsState()
    val monthlyProfit by viewModel.monthlyProfit.collectAsState()

    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    val currentMonth = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())

    LaunchedEffect(Unit) {
        viewModel.getDailyProfit(currentDate)
        viewModel.getMonthlyProfit(currentMonth)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Net Bénéfice Journalier: $dailyProfit CDF", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Net Bénéfice Mensuel: $monthlyProfit CDF", style = MaterialTheme.typography.headlineSmall)
    }
}
