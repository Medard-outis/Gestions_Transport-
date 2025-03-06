package bm.projetonewanda.transapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bm.projetonewanda.transapp.data.model.Vehicle
import bm.projetonewanda.transapp.viewmodel.VehicleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun VehicleScreen(navController: NavController, vehicleViewModel: VehicleViewModel) {
    val vehicles by vehicleViewModel.vehicles.collectAsState()

    LaunchedEffect(Unit) {
        vehicleViewModel.getAllVehicles()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Text(
            text = "Gestion des Véhicules",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFFF9800),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Grille de boutons
        GridButtons(navController)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(vehicles) { vehicle ->
                VehicleItem(vehicle, onDelete = { vehicleViewModel.deleteVehicle(vehicle) }, navController = navController)
            }
        }
    }
}

@Composable
fun GridButtons(navController: NavController) {
    val buttons = listOf(
        "Ajouter un Véhicule" to "vehicle_form",
        "Gérer les Chauffeurs" to "driver_screen",
        "Historique des Recettes" to "income_history",
        "Net Bénéfice" to "net_profit"
    )
    //#e4f4f3 = Color(0xFF03DAC5)

    val buttonColors = listOf(
        Color(0xFF03DAC5),
        Color(0xFF9E9E9E),
        Color(0xFFFF00AA),
        Color(0xFFFF9800)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(buttons.size) { index ->
            ActionButton(
                text = buttons[index].first,
                onClick = { navController.navigate(buttons[index].second) },
                couleurFond = buttonColors[index]
            )
        }
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit,couleurFond:Color=Color(0xFF6200EE)) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = couleurFond)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun VehicleItem(vehicle: Vehicle, onDelete: () -> Unit, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Plaque: ${vehicle.plaque}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Modèle: ${vehicle.model}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Type: ${vehicle.type}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1f).padding(end = 4.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                Text(text = "Supprimer", color = Color.White)
            }
                Button(
                    onClick = { navController.navigate("income_form/${vehicle.id}") },
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                Text(text = "Recette")
            }
            }
        }
    }
}



