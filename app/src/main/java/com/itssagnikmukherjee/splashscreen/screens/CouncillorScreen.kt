package com.itssagnikmukherjee.splashscreen.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel
import com.itssagnikmukherjee.splashscreen.backend.Task
import com.itssagnikmukherjee.splashscreen.backend.TaskViewModel
import com.itssagnikmukherjee.splashscreen.ui.theme.myGrey
import com.itssagnikmukherjee.splashscreen.ui.theme.myOrange
import com.itssagnikmukherjee.splashscreen.ui.theme.outfit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CouncillorScreen(viewModel: ComplaintViewModel = viewModel()) {





    Scaffold { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TopSec()
                Complaints()
            }
            var showDialog by remember { mutableStateOf(false) }
            Button(onClick = { showDialog = true }, modifier = Modifier.padding(top = 50.dp)) {
                Text(text = "Show Local Works")
            }
            if (showDialog) {
                LocalWorksDialog(onDismiss = { showDialog = false })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Complaints(viewModel: ComplaintViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchComplaints()
    }
    val complaints by viewModel.complaints.collectAsState()
    val taskViewModel: ComplaintViewModel = viewModel()
    LaunchedEffect(Unit) {
        taskViewModel.fetchTasks()
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Complaints",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(end = 10.dp)
        )
        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))){
            Text(text = "${complaints.size}", fontSize = 16.sp, modifier = Modifier
                .background(
                    Color.Red
                )
                .padding(5.dp), color = Color.White)
        }
    }

    Row {
        Chip(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 8.dp)) {
            Text(text = "Pending")
        }
        Chip(onClick = { /*TODO*/ }) {
            Text(text = "Forwarded")
        }
    }

    LazyColumn {
        items(complaints.reversed()) { complaint ->
            ComplaintCard(complaint = complaint)
        }
    }
}
@Composable
fun LocalWorksDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        // Use a Box to handle the size of the dialog
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Local Works",
                    fontSize = 24.sp,
                    fontFamily = outfit,
                    fontWeight = FontWeight.SemiBold,
                    color = myGrey,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Make TaskScreen take as much space as needed
                TaskScreen(viewModel())

                Spacer(modifier = Modifier.weight(1f)) // Push the button to the bottom

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Close")
                }
            }
        }
    }
}


@Composable
fun TopSec() {
    Column{
        Text(text = "Mrs. Lila Roy", fontSize = 30.sp, fontFamily = outfit, modifier = Modifier.padding(bottom = 15.dp),fontWeight = FontWeight.SemiBold, color = myGrey)
        Row {
            Icon(painter = painterResource(id = R.drawable.user), contentDescription = "", modifier = Modifier
                .size(25.dp)
                .padding(end = 10.dp), tint = myOrange)
            Text(text = "Councillor  |  Mohishila , Asansol", fontSize = 16.sp, fontFamily = outfit, color = myGrey)
        }
        Row {
            Icon(painter = painterResource(id = R.drawable.location), contentDescription = "", modifier = Modifier
                .size(25.dp)
                .padding(end = 10.dp), tint = myOrange)
            Text(text = "Asansol Municipal Corporation  |  713303", color = myGrey)
        }
    }
}

@Composable
fun ComplaintCard(complaint: Complaint) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Full Name: ${complaint.fullname}")
            Text(text = "Phone: ${complaint.phone}")
            Text(text = "Email: ${complaint.email}")
            Text(text = "Pincode: ${complaint.pin}")
            Text(text = "Problem: ${complaint.problem}")
            if (complaint.attachment_id?.isNotEmpty() == true) {
                Text(text = "Attachment ID: ${complaint.attachment_id}")
            }

            // Actions Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Handle info click */ }) {
                    Icon(Icons.Default.Info, contentDescription = "Info")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
                IconButton(onClick = { /* Handle share click */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }
            }
        }
    }
}

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks = viewModel.tasks.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        if (tasks.isEmpty()) {
            Text(text = "No tasks available")
        } else {
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(task = task)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ){

        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "ID: ${task._id}")
            Text(text = "Locality: ${task.locality}")
            Text(text = "Work Type: ${task.work_type}")
            Text(text = "Department Level: ${task.department_level}")
            Text(text = "Start Date: ${task.start_date}")
            Text(text = "End Date: ${task.end_date}")
            Text(text = "Head IDs: ${task.head_ids.joinToString()}")
            Text(text = "Resources: ${task.resources.joinToString()}")
        }
    }
}