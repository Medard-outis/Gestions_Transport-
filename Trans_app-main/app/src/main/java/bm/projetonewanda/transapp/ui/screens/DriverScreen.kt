package bm.projetonewanda.transapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bm.projetonewanda.transapp.data.model.Driver
import bm.projetonewanda.transapp.viewmodel.DriverViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
@Composable
fun DriverScreen(navController: NavController, driverViewModel: DriverViewModel) {
    val drivers by driverViewModel.drivers.collectAsState()

    LaunchedEffect(Unit) {
        driverViewModel.getAllDrivers()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Gestion des Chauffeurs",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFFFF9800),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("driver_form") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
        ) {
            Text(text = "Ajouter un chauffeur")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(drivers) { driver ->
                DriverItem(driver = driver, onDelete = { driverViewModel.deleteDriver(driver) })
            }
        }
    }
}


@Composable
fun DriverItem(driver: Driver, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nom: ${driver.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Contact: ${driver.contact}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Véhicule: ${driver.vehicleId}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = "Supprimer")
            }
        }
    }
}
