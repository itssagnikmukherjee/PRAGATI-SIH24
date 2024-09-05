package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var id by remember { mutableStateOf("") }
            var pass by remember { mutableStateOf("") }

            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text(text = "Enter your unique ID") }
            )
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text(text = "Enter your password") }
            )

            Button(onClick = {
                when {
                    id == "123" && pass == "123" -> {
                        navController.navigate("councillorscreen")
                    }
                    id == "234" && pass == "234" -> {
                        // Navigate to the contractor screen
                    }
                    id == "345" && pass == "345" -> {
                        navController.navigate("officerscreen")
                    }
                    id == "456" && pass == "456" -> {
                        // Navigate to the tech expert screen
                    }
                }
            }) {
                Text(text = "Login")
            }
        }
    }
}