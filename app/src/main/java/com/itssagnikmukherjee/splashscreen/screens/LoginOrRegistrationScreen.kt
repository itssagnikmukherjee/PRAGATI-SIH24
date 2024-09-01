package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun LoginOrReg(navController: NavController) {
    Scaffold {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate("loginscreen") }) {
                Text(text = "Login")
            }
            Button(onClick = { navController.navigate("individual") }) {
                Text(text = "Raise Complaint")
            }
            Button(onClick = { navController.navigate("contractorreg") }) {
                Text(text = "Contractor Registration")
            }
            Button(onClick = { navController.navigate("councillorreg") }) {
                Text(text = "Councillor Registration")
            }
            Button(onClick = { navController.navigate("registrationscreen") }) {
                Text(text = "Departmental Registration")
            }
        }
    }
}