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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.ui.theme.mukta
import com.itssagnikmukherjee.splashscreen.ui.theme.myBGC
import com.itssagnikmukherjee.splashscreen.ui.theme.myGrey
import com.itssagnikmukherjee.splashscreen.ui.theme.myOrange
import com.itssagnikmukherjee.splashscreen.ui.theme.outfit
import kotlinx.coroutines.launch

//@Composable
//fun LoginOrReg(navController: NavController) {
//    Scaffold {paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Button(onClick = { navController.navigate("loginscreen") }) {
//                Text(text = "Login")
//            }
//            Button(onClick = { navController.navigate("individual") }) {
//                Text(text = "Raise Complaint")
//            }
//            Button(onClick = { navController.navigate("contractorreg") }) {
//                Text(text = "Contractor Registration")
//            }
//            Button(onClick = { navController.navigate("councillorreg") }) {
//                Text(text = "Councillor Registration")
//            }
//            Button(onClick = { navController.navigate("registrationscreen") }) {
//                Text(text = "Departmental Registration")
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogReg(navController: NavController) {
    // State for managing the visibility of the sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    // Track if the sheet should be shown
    var isSheetVisible by remember { mutableStateOf(false) }

    // Function to show the bottom sheet
    val showSheet = {
        coroutineScope.launch {
            isSheetVisible = true
            sheetState.show()
        }
    }

    // Function to hide the bottom sheet
    val hideSheet = {
        coroutineScope.launch {
            sheetState.hide()
            isSheetVisible = false
        }
    }

    // Main content with the buttons and sheet trigger
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
            onClick = { showSheet() },
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
            onClick = { /* Handle registration */ },
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

    // Show the ModalBottomSheet only when isSheetVisible is true
    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { hideSheet() },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = outfit,
                    color = myGrey
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = "", // Replace with state variable
                    onValueChange = { /* Update state */ },
                    label = { Text("Unique ID") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.user), contentDescription = "") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = "", // Replace with state variable
                    onValueChange = { /* Update state */ },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.desc), contentDescription = "") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Handle login */ hideSheet() },
                    colors = ButtonDefaults.buttonColors(containerColor = myOrange),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = "LOGIN", color = Color.White, fontFamily = outfit, fontSize = 16.sp)
                }
            }
        }
    }
}
