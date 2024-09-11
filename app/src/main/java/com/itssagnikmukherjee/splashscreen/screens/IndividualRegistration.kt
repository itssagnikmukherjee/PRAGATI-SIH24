import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.itssagnikmukherjee.pragati.R
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
    var ind_location by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }
    var attachmentId by remember { mutableStateOf("") }

    // State variables for error messages
    var fullnameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }
    var locationError by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf("") }
    var problemError by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "File Complaint",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = outfit,
                color = myGrey,
                modifier = Modifier.height(60.dp),
            )
            Text(
                text = "Please provide the details below \nto file your complaint",
                fontFamily = outfit,
                color = myGrey,
                fontSize = 16.sp,
                modifier = Modifier.height(70.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Full Name TextField with validation
        TextField(
            value = fullname,
            onValueChange = {
                fullname = it
                fullnameError = if (fullname.isBlank()) "Full name is required" else ""
            },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            isError = fullnameError.isNotBlank()
        )
        if (fullnameError.isNotBlank()) {
            Text(text = fullnameError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Email TextField with validation
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (email.isBlank()) "Email is required" else ""
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            isError = emailError.isNotBlank()
        )
        if (emailError.isNotBlank()) {
            Text(text = emailError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Phone TextField with validation
        TextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = if (phone.isBlank()) "Phone is required" else ""
            },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = phoneError.isNotBlank()
        )
        if (phoneError.isNotBlank()) {
            Text(text = phoneError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Location TextField with validation
        TextField(
            value = ind_location,
            onValueChange = {
                ind_location = it
                locationError = if (ind_location.isBlank()) "Location is required" else ""
            },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            isError = locationError.isNotBlank()
        )
        if (locationError.isNotBlank()) {
            Text(text = locationError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // PIN TextField with validation
        TextField(
            value = pin,
            onValueChange = {
                pin = it
                pinError = if (pin.isBlank()) "PIN is required" else ""
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("PIN") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.pin),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            isError = pinError.isNotBlank()
        )
        if (pinError.isNotBlank()) {
            Text(text = pinError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Problem Description TextField with validation
        TextField(
            value = problem,
            onValueChange = {
                problem = it
                problemError = if (problem.isBlank()) "Problem description is required" else ""
            },
            label = { Text("Problem Description") },
            maxLines = 3,
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.desc),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            },
            isError = problemError.isNotBlank()
        )
        if (problemError.isNotBlank()) {
            Text(text = problemError, color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Optional Attachment ID TextField
        TextField(
            value = attachmentId,
            onValueChange = { attachmentId = it },
            label = { Text("Attachment ID (optional)") },
            modifier = Modifier.fillMaxWidth(0.8f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.clip),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        )
        Spacer(modifier = Modifier.height(60.dp))

        // Submit Button with validation check
        androidx.compose.material3.Button(
            onClick = {
                if (fullname.isBlank()) fullnameError = "Full name is required"
                if (email.isBlank()) emailError = "Email is required"
                if (phone.isBlank()) phoneError = "Phone is required"
                if (ind_location.isBlank()) locationError = "Location is required"
                if (pin.isBlank()) pinError = "PIN is required"
                if (problem.isBlank()) problemError = "Problem description is required"

                // Check if all required fields are filled
                if (fullnameError.isEmpty() && emailError.isEmpty() && phoneError.isEmpty() &&
                    locationError.isEmpty() && pinError.isEmpty() && problemError.isEmpty()) {
                    viewModel.submitComplaint(
                        fullname = fullname,
                        email = email,
                        phone = phone,
                        location = ind_location,
                        pin = pin,
                        problem = problem,
                        attachmentId = attachmentId
                    )
                    navController.navigate("complaintSubmitted/$fullname/$ind_location/$pin")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = myOrange),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "FILE COMPLAINT",
                color = Color.White,
                fontFamily = outfit,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isSubmitting) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.submitMessage, fontFamily = outfit, fontSize = 16.sp, color = myGrey)
    }
}
