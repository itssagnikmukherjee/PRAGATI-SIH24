package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itssagnikmukherjee.pragati.R

import com.itssagnikmukherjee.splashscreen.ui.theme.mukta
import com.itssagnikmukherjee.splashscreen.ui.theme.myBGC
import com.itssagnikmukherjee.splashscreen.ui.theme.myGrey
import com.itssagnikmukherjee.splashscreen.ui.theme.myOrange
import com.itssagnikmukherjee.splashscreen.ui.theme.outfit
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogReg(navController: NavController) {
    // State for managing the visibility of the login sheet
    val loginSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    // Track if the login sheet should be shown
    var isLoginSheetVisible by remember { mutableStateOf(false) }

    // State for managing the visibility of the registration options sheet
    val registrationOptionsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    // Track if the registration options sheet should be shown
    var isRegistrationOptionsSheetVisible by remember { mutableStateOf(false) }

    // Function to show the login sheet
    val showLoginSheet = {
        coroutineScope.launch {
            isLoginSheetVisible = true
            loginSheetState.show()
        }
    }

    // Function to hide the login sheet
    val hideLoginSheet = {
        coroutineScope.launch {
            loginSheetState.hide()
            isLoginSheetVisible = false
        }
    }

    // Function to show the registration options sheet
    val showRegistrationOptionsSheet = {
        coroutineScope.launch {
            isRegistrationOptionsSheetVisible = true
            registrationOptionsSheetState.show()
        }
    }

    // Function to hide the registration options sheet
    val hideRegistrationOptionsSheet = {
        coroutineScope.launch {
            registrationOptionsSheetState.hide()
            isRegistrationOptionsSheetVisible = false
        }
    }

    // Main content with the buttons and sheet triggers
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 40.dp)
        ) {
            Text(
                text = "Let's get\nStarted",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = outfit,
                color = myGrey,
                modifier = Modifier.height(100.dp)
            )
            Text(
                text = "Log in with your Unique ID, or \nregister if a first-time user",
                fontFamily = outfit,
                fontWeight = FontWeight.Light,
                color = myGrey,
                fontSize = 16.sp,
                modifier = Modifier.height(50.dp)
            )
            Text(
                text = "\nFile a complaint for any local issues",
                fontFamily = outfit,
                fontWeight = FontWeight.Light,
                color = myGrey,
                fontSize = 16.sp,
                modifier = Modifier.height(50.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.pragitiimg),
            contentDescription = "",
            modifier = Modifier.size(350.dp)
        )
        TextButton(
            onClick = { navController.navigate("individual") },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth(.9f)
                .padding(bottom = 20.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "FILE COMPLAINT",
                color = myOrange,
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { showLoginSheet() },
            colors = ButtonDefaults.buttonColors(containerColor = myGrey),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "LOGIN",
                color = Color.White,
                fontFamily = outfit,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { showRegistrationOptionsSheet() },
            colors = ButtonDefaults.buttonColors(containerColor = myOrange),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "REGISTER",
                color = Color.White,
                fontFamily = outfit,
                fontSize = 16.sp
            )
        }
    }

    // Show the ModalBottomSheet only when isLoginSheetVisible is true
    if (isLoginSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { hideLoginSheet() },
            sheetState = loginSheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var id by remember { mutableStateOf("") }
                var pass by remember { mutableStateOf("") }

                Column (modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(bottom = 20.dp)){
                    Text(
                        text = "Pragati  Login",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = outfit,
                        color = myGrey,
                        textAlign = TextAlign.Start
                    )
                    Text(text = "Enter your Unique ID and Password below", fontFamily = outfit, color = myGrey, fontSize = 16.sp, modifier = Modifier.padding(0.dp, 10.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = id,
                    onValueChange = { id = it },
                    label = { Text("Unique ID") },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.id), contentDescription = "", modifier = Modifier.size(20.dp)) }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(.9f),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = "", modifier = Modifier.size(20.dp)) },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        hideLoginSheet()
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
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = myGrey),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "LOGIN",
                        color = Color.White,
                        fontFamily = outfit,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    // Show the ModalBottomSheet only when isRegistrationOptionsSheetVisible is true
    if (isRegistrationOptionsSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { hideRegistrationOptionsSheet() },
            sheetState = registrationOptionsSheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(.9f)
                ){
                Text(
                    text = "Registration Options",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = outfit,
                    color = myGrey,
                    modifier = Modifier.height(60.dp)
                )
                Text(text = "Choose your role and complete \n your registration effortlessly", fontFamily = outfit, color = myGrey, fontSize = 16.sp, modifier = Modifier.padding(bottom = 30.dp))
                }
                Button(
                    onClick = { showRegistrationOptionsSheet()
                        navController.navigate("councillorreg")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = myOrange),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Councillor Registration",
                        color = Color.White,
                        fontFamily = outfit,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        showRegistrationOptionsSheet()
                        navController.navigate("contractorreg")
                              },
                    colors = ButtonDefaults.buttonColors(containerColor = myOrange),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Contractor Registration",
                        color = Color.White,
                        fontFamily = outfit,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { showRegistrationOptionsSheet()
                              navController.navigate("registrationscreen")
                              },
                    colors = ButtonDefaults.buttonColors(containerColor = myOrange),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Departmental Registration",
                        color = Color.White,
                        fontFamily = outfit,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
