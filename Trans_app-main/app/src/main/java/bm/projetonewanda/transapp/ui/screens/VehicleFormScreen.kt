package bm.projetonewanda.transapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bm.projetonewanda.transapp.data.model.Vehicle
import bm.projetonewanda.transapp.viewmodel.VehicleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleFormScreen(vehicleViewModel: VehicleViewModel, onBack: () -> Unit) {
    var plaque by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Actif") }

    var plaqueError by remember { mutableStateOf(false) }
    var modelError by remember { mutableStateOf(false) }
    var typeError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ajouter un Véhicule",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFFF9800),
            modifier = Modifier.padding(bottom = 24.dp)
        )


        OutlinedTextField(
            value = plaque,
            onValueChange = { plaque = it; plaqueError = false },
            label = { Text("Plaque") },
            isError = plaqueError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (plaqueError) {
            Text(
                text = "La plaque est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = model,
            onValueChange = { model = it; modelError = false },
            label = { Text("Modèle") },
            isError = modelError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (modelError) {
            Text(
                text = "Le modèle est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { type = it; typeError = false },
            label = { Text("Type (Moto, Taxi, Bus)") },
            isError = typeError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (typeError) {
            Text(
                text = "Le type est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                plaqueError = plaque.isEmpty()
                modelError = model.isEmpty()
                typeError = type.isEmpty()

                if (!plaqueError && !modelError && !typeError) {
                    val vehicle = Vehicle(0, plaque, model, type, "Disponible")
                    vehicleViewModel.addVehicle(vehicle)
                    onBack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
        ) {
            Text(text = "Enregistrer", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Annuler", color = Color.White)
        }
    }
}