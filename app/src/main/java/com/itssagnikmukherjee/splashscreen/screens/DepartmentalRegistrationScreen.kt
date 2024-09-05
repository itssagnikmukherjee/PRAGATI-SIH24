package com.itssagnikmukherjee.splashscreen.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.provider.DocumentsContract
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .weight(1f)
            ) {
                HorizontalPager(
                    count = 4,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> RegDetails()
                        1 -> RegistrationScreen2()
                        2 -> LocationInfo()
                        3 -> UploadScreen()
                    }
                }
            }
            Row {
                Button(onClick = {scope.launch{pagerState.animateScrollToPage(pagerState.currentPage-1)}}){
                    Text(text = "Previous")
                }
            Button(onClick = {scope.launch{pagerState.animateScrollToPage(pagerState.currentPage+1)}}){
                Text(text = "Next")
            }
            }
            LinearProgressIndicator(
                progress = (pagerState.currentPage + 1) / 4f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun RegDetails() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
    var profile by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> profile = uri }
    )
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(shape = CircleShape)
            .clickable { imagePickerLauncher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {

            if (profile != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Add Profile Picture",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var mobileNo by remember { mutableStateOf("+91") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Full Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email Address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mobileNo,
            onValueChange = {mobileNo=it},
            label = { Text(text = "Phone Number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen2() {
    var selectedDepartmentLevel by remember{ mutableStateOf("") }
    var selectedDepartment by remember { mutableStateOf("") }
    var expandedLevel by remember { mutableStateOf(false) }
    var expandedDepartment by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("") }
    var expandedRole by remember { mutableStateOf(false) }
    var employeeNo by remember { mutableStateOf("") }

    val departmentLevels = listOf("State Department", "Local Department")
    val localDepartments = listOf(
        "Municipality", "Municipal Corporation", "Nagar Panchayat", "Gramin Panchayat"
    )
    val stateDepartments = listOf(
        "Public Works Department", "Urban Planning Department", "Environmental Department",
        "Water Supply Department", "Health Department", "Electricity Department"
    )
    val jobRole = listOf("Technical Expert","Officers / Head of Department")

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Department Level Dropdown
                ExposedDropdownMenuBox(
                    expanded = expandedLevel,
                    onExpandedChange = { expandedLevel = !expandedLevel }
                ) {
                    TextField(
                        value = selectedDepartmentLevel,
                        onValueChange = { selectedDepartmentLevel = it },
                        readOnly = true,
                        label = { Text("Select Department Level") },
                        trailingIcon = {
                            Icon(
                                imageVector = if (expandedLevel) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedLevel,
                        onDismissRequest = { expandedLevel = false }
                    ) {
                        departmentLevels.forEach { level ->
                            DropdownMenuItem(
                                text = { Text(text = level) },
                                onClick = {
                                    selectedDepartmentLevel = level
                                    selectedDepartment = "" // Reset department when level changes
                                    expandedLevel = false
                                }
                            )
                        }
                    }
                }

                // Department Dropdown - enabled only if department level is selected
                val departments = when (selectedDepartmentLevel) {
                    "State Department" -> stateDepartments
                    "Local Department" -> localDepartments
                    else -> emptyList()
                }

                ExposedDropdownMenuBox(
                    expanded = expandedDepartment,
                    onExpandedChange = { expandedDepartment = !expandedDepartment }
                ) {
                    TextField(
                        value = selectedDepartment,
                        onValueChange = { selectedDepartment = it },
                        readOnly = true,
                        enabled = departments.isNotEmpty(),
                        label = { Text("Select Department") },
                        trailingIcon = {
                            Icon(
                                imageVector = if (expandedDepartment && departments.isNotEmpty()) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedDepartment,
                        onDismissRequest = { expandedDepartment = false }
                    ) {
                        departments.forEach { department ->
                            DropdownMenuItem(
                                text = { Text(text = department) },
                                onClick = {
                                    selectedDepartment = department
                                    expandedDepartment = false
                                }
                            )
                        }
                    }
                }

                // Role Dropdown Menu - enabled only if a department is selected
                ExposedDropdownMenuBox(
                    expanded = expandedRole,
                    onExpandedChange = { expandedRole = !expandedRole }
                ) {
                    TextField(
                        value = selectedRole,
                        onValueChange = { selectedRole = it },
                        readOnly = true,
                        enabled = selectedDepartment.isNotEmpty(),
                        label = { Text("Select Role") },
                        trailingIcon = {
                            Icon(
                                imageVector = if (expandedRole && selectedDepartment.isNotEmpty()) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedRole,
                        onDismissRequest = { expandedRole = false }
                    ) {
                        jobRole.forEach { role ->
                            DropdownMenuItem(
                                text = { Text(text = role) },
                                onClick = {
                                    selectedRole = role
                                    expandedRole = false
                                }
                            )
                        }
                    }
                }

                // Employee Number Input
                OutlinedTextField(
                    value = employeeNo,
                    onValueChange = { employeeNo = it },
                    label = { Text(text = "Employee No") },
                    modifier = Modifier.fillMaxWidth(),
                )
                // Add a button or action to proceed with registration
            }
        }
    }
}

@Composable
fun LocationInfo() {
    var location by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Office Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Pin Code") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

        }
    }
}

@Composable
fun UploadScreen() {
    val context = LocalContext.current
    val activityResultRegistry = LocalActivityResultRegistryOwner.current?.activityResultRegistry
    val uriHandler = LocalUriHandler.current

    // Define state for each file
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

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FileUploadField(
                label = "Upload Govt Issued ID",
                uri = govtIdUri,
                onClick = { getFile.launch("application/pdf") }
            )

            FileUploadField(
                label = "Upload Departmental Authorization Letter",
                uri = authorizationLetterUri,
                onClick = { getFile.launch("application/pdf") }
            )

            FileUploadField(
                label = "Upload Previous Projects",
                uri = previousProjectsUri,
                onClick = { getFile.launch("application/pdf") }
            )


            var passWord by remember { mutableStateOf("") }
            var confirmPassWord by remember { mutableStateOf("") }
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = passWord,
                        onValueChange = { passWord = it },
                        label = { Text("Create Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = confirmPassWord,
                        onValueChange = { confirmPassWord = it },
                        label = { Text("Confirm Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
}

@Composable
fun FileUploadField(label: String, uri: Uri?, onClick: () -> Unit) {
    val fileName = uri?.let {
        DocumentsContract.getDocumentId(it).substringAfterLast("/")
    } ?: "No file selected"

    Column {
        Text(text = label, style = MaterialTheme.typography.body1)
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select File")
        }
        Text(
            text = fileName,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}