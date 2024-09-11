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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itssagnikmukherjee.pragati.R
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel
import com.itssagnikmukherjee.splashscreen.backend.Task
import com.itssagnikmukherjee.splashscreen.backend.TaskViewModel
import com.itssagnikmukherjee.splashscreen.ui.theme.myBGC
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
            var showSheet by remember { mutableStateOf(false) }
            Button(onClick = { showSheet = true }, modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = -20.dp, y = 65.dp), colors = ButtonDefaults.buttonColors(myOrange), shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Local Works", fontFamily = outfit, color = Color.White)
            }
            if (showSheet) {
                LocalWorksBottomSheet(onDismiss = {showSheet=false}, TaskViewModel())
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

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Complaints",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 22.sp,
                fontFamily = outfit,
                color = myGrey,
                fontWeight = FontWeight.SemiBold
            )
            Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
                Text(
                    text = "${complaints.size}", fontSize = 16.sp, modifier = Modifier
                        .background(
                            myOrange
                        )
                        .padding(5.dp), color = Color.White
                )
            }
        }
        
        Text(text = "submitted by local residents", fontFamily = outfit, color = myGrey, modifier = Modifier.padding(start = 10.dp))
        
    }

    Row(
        modifier = Modifier.padding(start = 10.dp)
    ){
        Chip(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 8.dp), colors = ChipDefaults.chipColors(
            myGrey), shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Pending", fontFamily = outfit, color = Color.White)
        }
        Chip(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 8.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Forwarded", fontFamily = outfit, color = myGrey)
        }
    }

    LazyColumn {
        items(complaints.reversed()) { complaint ->
            ComplaintCard(complaint = complaint)
        }
    }
}

//
//@Composable
//fun LocalWorksDialog(onDismiss: () -> Unit) {
//    Dialog(onDismissRequest = onDismiss) {
//        // Use a Box to handle the size of the dialog
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "Local Works",
//                    fontSize = 24.sp,
//                    fontFamily = outfit,
//                    fontWeight = FontWeight.SemiBold,
//                    color = myGrey,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Make TaskScreen take as much space as needed
//                TaskScreen(viewModel())
//
//                Spacer(modifier = Modifier.weight(1f)) // Push the button to the bottom
//
//                Button(
//                    onClick = onDismiss,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                ) {
//                    Text("Close")
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalWorksBottomSheet(onDismiss: () -> Unit, viewModel: TaskViewModel) {
    val tasks = viewModel.tasks.collectAsState().value
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        scrimColor = Color.Black.copy(alpha = 0.5f)
    ) {
        // Use a Box to handle the size of the sheet
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.padding(start = 20.dp)
                ){
                    Text(
                        text = "Local Works",
                        fontSize = 24.sp,
                        fontFamily = outfit,
                        fontWeight = FontWeight.SemiBold,
                        color = myGrey,
                        modifier = Modifier.padding(bottom = 8.dp, end = 15.dp)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                    ){
    

                    Text(text = "${tasks.size}", fontSize = 20.sp, modifier = Modifier
                        .size(30.dp)
                        .background(
                            myOrange
                        ), textAlign = TextAlign.Center, lineHeight = 30.sp, color = Color.White)
                    }
                }
                Row(
                    modifier = Modifier.padding(start = 20.dp)
                ){
                Text(text = "in Asansol, Paschim Bardhaman \nWest Bengal  |  713303", fontFamily = outfit, fontSize = 14.sp, color = myGrey)
                }

                // Make TaskScreen take as much space as needed
                TaskScreen(viewModel())

                Spacer(modifier = Modifier.weight(1f)) // Push the button to the bottom

                androidx.compose.material3.Button(
                    onClick = {onDismiss()},
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = myOrange),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Close",
                        color = Color.White,
                        fontFamily = outfit,
                        fontSize = 16.sp
                    )
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
            .padding(8.dp),
        colors = CardDefaults.cardColors(myBGC)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painterResource(id = R.drawable.problem), contentDescription = "", modifier = Modifier.size(20.dp), tint = Color.Black.copy(0.7f))
            Text(text = "${complaint.problem}", modifier = Modifier.padding(start = 10.dp), fontSize = 24.sp, fontFamily = outfit, color = Color.Black.copy(0.7f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painterResource(id = R.drawable.location), contentDescription = "", modifier = Modifier.size(20.dp), tint = Color.Black.copy(0.7f))
                Text(text = "${complaint.location}", modifier = Modifier.padding(start = 10.dp), fontSize = 18.sp, fontFamily = outfit, color = Color.Black.copy(0.7f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(painterResource(id = R.drawable.pin), contentDescription = "", modifier = Modifier.size(20.dp), tint = Color.Black.copy(0.7f))
                Text(text = "${complaint.pin}", modifier = Modifier.padding(start = 10.dp), fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = outfit, color = Color.Black.copy(0.7f))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.user),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black.copy(0.7f)
                    )
                    Text(
                        text = "${complaint.fullname}",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 15.sp,
                        fontFamily = outfit,
                        color = Color.Black.copy(0.7f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.phone),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black.copy(0.7f)
                    )
                    Text(
                        text = "${complaint.phone}",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 15.sp,
                        fontFamily = outfit,
                        color = Color.Black.copy(0.7f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.email),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black.copy(0.7f)
                    )
                    Text(
                        text = "${complaint.email}",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 15.sp,
                        fontFamily = outfit,
                        color = Color.Black.copy(0.7f)
                    )
                }
            }
            if (complaint.attachment_id?.isNotEmpty() == true) {
                Text(text = "Attachment ID: ${complaint.attachment_id}")
            }

            // Actions Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Handle info click */ }) {
                    Icon(Icons.Default.Info, contentDescription = "Info", tint = Color.Black.copy(0.7f))
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.Black.copy(0.7f))
                }
                IconButton(onClick = { /* Handle share click */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.Black.copy(0.7f))
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
            .padding(top = 10.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.building), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "${task.work_type}", fontSize = 22.sp, fontFamily = outfit)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.location), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "${task.locality}")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.department), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "${task.department_level}", fontSize = 14.sp, fontFamily = outfit)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ) {
                    Icon(painterResource(id = R.drawable.calender), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "${task.start_date}")
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.calender), contentDescription = "", modifier = Modifier.size(20.dp) )
                    Text(text = "${task.end_date}", fontSize = 14.sp, fontFamily = outfit)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.id), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "Head IDs: ${task.head_ids.joinToString()}", fontSize = 14.sp, fontFamily = outfit)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.tools), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "Resources: ${task.resources.joinToString()}", fontSize = 14.sp, fontFamily = outfit)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(40.dp)
                ){
                    Icon(painterResource(id = R.drawable.id), contentDescription = "", modifier = Modifier.size(20.dp) )
                Text(text = "Task ID: ${task._id}", fontSize = 14.sp, fontFamily = outfit)
                }
            }
        }
    }
}