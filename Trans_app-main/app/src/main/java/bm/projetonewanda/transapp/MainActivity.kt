package bm.projetonewanda.transapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bm.projetonewanda.transapp.data.AppDatabase
import bm.projetonewanda.transapp.data.repository.DriverRepository
import bm.projetonewanda.transapp.data.repository.IncomeRepository
import bm.projetonewanda.transapp.data.repository.NetProfitRepository
import bm.projetonewanda.transapp.data.repository.VehicleRepository
import bm.projetonewanda.transapp.ui.screens.DriverFormScreen
import bm.projetonewanda.transapp.ui.screens.DriverScreen
import bm.projetonewanda.transapp.ui.screens.IncomeFormScreen
import bm.projetonewanda.transapp.ui.screens.IncomeHistoryScreen
import bm.projetonewanda.transapp.ui.screens.NetProfitScreen
import bm.projetonewanda.transapp.ui.screens.SplashScreen
import bm.projetonewanda.transapp.ui.screens.VehicleFormScreen
import bm.projetonewanda.transapp.ui.screens.VehicleScreen
import bm.projetonewanda.transapp.ui.theme.TransAppTheme
import bm.projetonewanda.transapp.viewmodel.DriverViewModel
import bm.projetonewanda.transapp.viewmodel.DriverViewModelFactory
import bm.projetonewanda.transapp.viewmodel.IncomeViewModel
import bm.projetonewanda.transapp.viewmodel.IncomeViewModelFactory
import bm.projetonewanda.transapp.viewmodel.NetProfitViewModelFactory
import bm.projetonewanda.transapp.viewmodel.VehicleViewModel
import bm.projetonewanda.transapp.viewmodel.VehicleViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vehicleRepository = VehicleRepository(AppDatabase.getDatabase(this).vehicleDao())
        val vehicleViewModelFactory = VehicleViewModelFactory(vehicleRepository)

        setContent {
            //val vehicleViewModel: VehicleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = vehicleViewModelFactory)
            AppNavigation()
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Vehicle
    val vehicleRepository = VehicleRepository(AppDatabase.getDatabase(context).vehicleDao())
    val vehicleViewModel: VehicleViewModel = viewModel(factory = VehicleViewModelFactory(vehicleRepository))

    // Driver
    val driverRepository = DriverRepository(AppDatabase.getDatabase(context).driverDao())
    val driverViewModel: DriverViewModel = viewModel(factory = DriverViewModelFactory(driverRepository))
    //Income
    val incomeRepository = IncomeRepository(AppDatabase.getDatabase(context).incomeDao())
    val incomeViewModel: IncomeViewModel = viewModel(factory = IncomeViewModelFactory(incomeRepository))
    // NetProfit
    val netProfitRepository = NetProfitRepository(AppDatabase.getDatabase(context).incomeDao())



    NavHost(navController, startDestination = "splash") {
        composable("vehicle_screen") {
            VehicleScreen(navController, vehicleViewModel)
        }

        composable("splash") {
            SplashScreen(navController)
        }
        composable("driver_screen") {
            DriverScreen(navController, driverViewModel)
        }
        composable("vehicle_form") {
            VehicleFormScreen(vehicleViewModel, onBack = { navController.popBackStack() })
        }
        composable("driver_form") {
            DriverFormScreen(driverViewModel, onBack = { navController.popBackStack() })
        }

        composable("income_form/{vehicleId}") { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toInt() ?: 0
            IncomeFormScreen(
                vehicleId = vehicleId,
                onBack = { navController.popBackStack() },
                incomeViewModel = incomeViewModel // Ici on passe bien le viewmodel 🔥
            )
        }
        composable("income_history") {
            IncomeHistoryScreen(incomeViewModel = incomeViewModel)
        }

        composable("net_profit") {
            NetProfitScreen(viewModel = viewModel(factory = NetProfitViewModelFactory(netProfitRepository)))
        }


    }
}



