package com.itssagnikmukherjee.splashscreen.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun CouncillorRegistration() {
    Scaffold {innerPadding->
        Column (
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            OutlinedTextField(value = "Full Name", onValueChange = {})
            OutlinedTextField(value = "Phone No", onValueChange = {})
            OutlinedTextField(value = "Email", onValueChange = {})
            OutlinedTextField(value = "Locality", onValueChange = {})
            OutlinedTextField(value = "Municipality", onValueChange = {})
            OutlinedTextField(value = "Pincode", onValueChange = {})
            OutlinedTextField(value = "Password", onValueChange = {})
            OutlinedTextField(value = "Confirm Password", onValueChange = {})

            var govtIdUri by remember { mutableStateOf<Uri?>(null) }
            var authorizationLetterUri by remember { mutableStateOf<Uri?>(null) }
            var previousProjectsUri by remember { mutableStateOf<Uri?>(null) }

            // Create result launcher
            val getFile = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    // Determine which file was selected and update state
                    when {
                        govtIdUri == null -> govtIdUri = it
                        authorizationLetterUri == null -> authorizationLetterUri = it
                        previousProjectsUri == null -> previousProjectsUri = it
                    }
                }
            }
            FileUploadField(
                label = "Upload Govt Issued ID",
                uri = govtIdUri,
                onClick = { getFile.launch("application/pdf") }
            )

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Register as Councillor")
            }

        }

    }
}