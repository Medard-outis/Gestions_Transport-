package bm.projetonewanda.transapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import bm.projetonewanda.transapp.data.model.Income
import bm.projetonewanda.transapp.viewmodel.IncomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@SuppressLint("NewApi")
@Composable
fun IncomeFormScreen(vehicleId: Int, onBack: () -> Unit, incomeViewModel: IncomeViewModel) {
    var courseNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ajouter Recette", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = courseNumber, onValueChange = { courseNumber = it }, label = { Text("Nombre de courses") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Montant total") })
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (courseNumber.isNotEmpty() && amount.isNotEmpty()) {
                val income = Income(
                    vehicleId = vehicleId,
                    date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()),
                    courseNumber = courseNumber.toInt(),
                    amount = amount.toDouble()
                )
                incomeViewModel.addIncome(income)
                onBack()
            }
        }) {
            Text(text = "Enregistrer")
        }
    }
}


