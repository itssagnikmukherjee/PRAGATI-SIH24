package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel

@Composable
fun DepartmentalHeadScreen(viewModel: ComplaintViewModel = viewModel()) {
    val forwardedComplaints by viewModel.forwardedComplaints.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Complaints from the councillor",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn {
                items(forwardedComplaints) { complaint ->
                    ComplaintItem(complaint)
                }
            }
        }
    }
}

@Composable
fun ComplaintItem(complaint: Complaint) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Full Name: ${complaint.fullname}")
            Text(text = "Phone: ${complaint.phone}")
            Text(text = "Email: ${complaint.email}")
            Text(text = "Pincode: ${complaint.pin}")
            Text(text = "Problem: ${complaint.problem}")
            if (complaint.attachment_id != null) {
                Text(text = "Attachment ID: ${complaint.attachment_id}")
            }
        }
    }
}