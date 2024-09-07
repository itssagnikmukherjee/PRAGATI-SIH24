package com.itssagnikmukherjee.splashscreen

import IndividualRegScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itssagnikmukherjee.splashscreen.screens.ComplaintSubmitted
import com.itssagnikmukherjee.splashscreen.screens.ContractorRegistration
import com.itssagnikmukherjee.splashscreen.screens.CouncillorRegistration
import com.itssagnikmukherjee.splashscreen.screens.CouncillorScreen
import com.itssagnikmukherjee.splashscreen.screens.DepartmentalHeadScreen
import com.itssagnikmukherjee.splashscreen.screens.LogReg
//import com.itssagnikmukherjee.splashscreen.screens.LogReg
import com.itssagnikmukherjee.splashscreen.screens.LoginScreen
import com.itssagnikmukherjee.splashscreen.screens.OnboardingScreen
import com.itssagnikmukherjee.splashscreen.screens.RegistrationScreen
import com.itssagnikmukherjee.splashscreen.screens.SplashScreen
import com.itssagnikmukherjee.splashscreen.ui.theme.SplashScreenTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash"){
                        composable("splash"){ SplashScreen(navController,context = this@MainActivity)}
                        composable("onboarding"){ OnboardingScreen(navController,context=this@MainActivity) }
//                        composable("loginreg"){ LoginOrReg(navController) }
                        composable("loginreg"){ LogReg(navController) }
                        composable("registrationscreen"){ RegistrationScreen(navController) }
                        composable("loginscreen"){ LoginScreen(navController) }
                        composable("individual"){ IndividualRegScreen(navController = navController) }
                        composable("contractorreg"){ContractorRegistration()}
                        composable("councillorreg"){ CouncillorRegistration()}
                        composable("councillorscreen"){ CouncillorScreen() }
                        composable("officerscreen"){ DepartmentalHeadScreen() }
                        composable(
                            route = "complaintsubmitted/{ind_name}/{ind_location}/{ind_pin}",
                            arguments = listOf(
                                navArgument("ind_name") { type = NavType.StringType },
                                navArgument("ind_location") { type = NavType.StringType },
                                navArgument("ind_pin") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            // Retrieve the arguments correctly
                            val ind_name = backStackEntry.arguments?.getString("ind_name") ?: ""
                            val ind_location = backStackEntry.arguments?.getString("ind_location") ?: ""
                            val ind_pin = backStackEntry.arguments?.getString("ind_pin") ?: ""

                            // Pass the arguments to the ComplaintSubmitted Composable if needed
                            ComplaintSubmitted(name = ind_name,location= ind_location, pin = ind_pin)
                        }
                    }
                }
            }
        }
    }
}
