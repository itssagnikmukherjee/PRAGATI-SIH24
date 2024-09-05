package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContractorScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Complaints")
//            LazyColumn {
//                items(complaintList()) { complaint ->
//                    ComplaintCard(complaint)
//                }
//            }
//            Text(text = "Locality Works")
//            LazyColumn {
//                items(LocalityWorkList()){work->
//                    LocalityWorkCard(work)
//                }
//            }
        }
    }
}


//@Composable
//fun BidsCard(work: LocalityWork) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(10.dp)
//    ){
//        Row (
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Text(text = work.locality)
//            Text(text = work.pincode)
//            Text(text = work.work)
//            Text(text = work.date)
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.Default.Info, contentDescription = "")
//            }
//        }
//    }
//}
//
//fun BidList(): List<LocalityWork> {
//    return listOf(
//        LocalityWork("Asansol","713303","Road","12-12-2023"),
//        LocalityWork("Burnpur","713305","Water","12-12-2023"),
//    )
//}

data class Bid(
    val id: String,
    val pincode: String,
    val work: String,
    val date: String,
)

//
//@Composable
//fun ComplaintCard(complaint: Complaint) {
//    val complaintList = complaintList()
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(10.dp)
//    ){
//        Row (
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Text(text = complaint.locality)
//            Text(text = complaint.pincode)
//            Text(text = complaint.problem)
//            Text(text = complaint.name)
//            Text(text = complaint.document)
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.Default.Share, contentDescription = "")
//            }
//        }
//    }
//}
//
//data class Complaint(
//    val name: String,
//    val phNo : String,
//    val problem: String,
//    val locality: String,
//    val pincode: String,
//    val document: String
//)
//
//fun complaintList(): List<Complaint> {
//    return listOf(
//        Complaint("Binod","123456789","Road","Asansol","713303","complaint1.pdf"),
//        Complaint("Sagar","123456789","Road","Asansol","713303","complaint2.pdf"),
//        )
//}