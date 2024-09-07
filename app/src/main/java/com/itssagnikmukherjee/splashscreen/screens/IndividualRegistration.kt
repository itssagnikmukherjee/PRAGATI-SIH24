import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel
import com.itssagnikmukherjee.splashscreen.ui.theme.myGrey
import com.itssagnikmukherjee.splashscreen.ui.theme.myOrange
import com.itssagnikmukherjee.splashscreen.ui.theme.outfit

@Composable
fun IndividualRegScreen(viewModel: ComplaintViewModel = viewModel(), navController: NavController) {
    // State variables for form fields
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }
    var attachmentId by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Column(
            modifier = Modifier.fillMaxWidth(.8f)
        ){
            Text(text = "File Complaint", fontSize = 36.sp, fontWeight = FontWeight.Bold, fontFamily = outfit, color = myGrey, modifier = Modifier.height(60.dp))
            Text(text = "Please provide the details below \nto file your complaint", fontFamily = outfit, color = myGrey, fontSize = 16.sp, modifier = Modifier.height(70.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.user), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.email), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.phone), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = pin,
            onValueChange = { pin = it },
            label = { Text("PIN") },
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.location), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = problem,
            onValueChange = { problem = it },
            label = { Text("Problem Description") },
            maxLines = 3,
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.desc), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = attachmentId,
            onValueChange = { attachmentId = it },
            label = { Text("Attachment ID (optional)") },
            modifier = Modifier.fillMaxWidth(.8f),
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.clip), contentDescription = "", modifier = Modifier.size(20.dp))}
        )
        Spacer(modifier = Modifier.height(60.dp))
//        Button(
//            onClick = {
//                viewModel.submitComplaint(
//                    fullname = fullname,
//                    email = email,
//                    phone = phone,
//                    pin = pin,
//                    problem = problem,
//                    attachmentId = attachmentId
//                )
//            },
//            enabled = !viewModel.isSubmitting,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("File Complaint")
//        }
        androidx.compose.material3.Button(
            onClick = {
                viewModel.submitComplaint(
                    fullname = fullname,
                    email = email,
                    phone = phone,
                    pin = pin,
                    problem = problem,
                    attachmentId = attachmentId
               )
                navController.navigate("complaintSubmitted")
            },
            colors = ButtonDefaults.buttonColors(containerColor = myOrange),
            modifier = Modifier
                .fillMaxWidth(.8f)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "FILE  COMPLAINT", color = Color.White, fontFamily = outfit, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.isSubmitting) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.submitMessage, fontFamily = outfit, fontSize = 16.sp, color = myGrey)
    }
}
