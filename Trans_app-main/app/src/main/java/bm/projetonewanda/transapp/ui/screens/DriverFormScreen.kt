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
import bm.projetonewanda.transapp.data.model.Driver
import bm.projetonewanda.transapp.viewmodel.DriverViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverFormScreen(driverViewModel: DriverViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var vehicleId by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var contactError by remember { mutableStateOf(false) }
    var vehicleIdError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ajouter un Chauffeur",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF6200EE),
            modifier = Modifier.padding(bottom = 24.dp)
        )



        OutlinedTextField(
            value = name,
            onValueChange = { name = it; nameError = false },
            label = { Text("Nom") },
            isError = nameError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (nameError) {
            Text(
                text = "Le nom est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it; contactError = false },
            label = { Text("Contact") },
            isError = contactError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (contactError) {
            Text(
                text = "Le contact est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = vehicleId,
            onValueChange = { vehicleId = it; vehicleIdError = false },
            label = { Text("ID Véhicule") },
            isError = vehicleIdError,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red
            )
        )
        if (vehicleIdError) {
            Text(
                text = "L'ID Véhicule est obligatoire",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                nameError = name.isEmpty()
                contactError = contact.isEmpty()
                vehicleIdError = vehicleId.isEmpty()

                if (!nameError && !contactError && !vehicleIdError) {
                    val driver = Driver(0, name, contact, vehicleId.toInt())
                    driverViewModel.addDriver(driver)
                    onBack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)), // Moins arrondi
            shape = RoundedCornerShape(8.dp), // Moins arrondi
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text(text = "Enregistrer", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp), // Moins arrondi
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text = "Annuler", color = Color.White)
        }
    }
}
