import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel

@Composable
fun IndividualRegScreen(viewModel: ComplaintViewModel = viewModel()) {
    // State variables for form fields
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }
    var attachmentId by remember { mutableStateOf("") }

    // UI layout for the screen
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Full Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = pin,
            onValueChange = { pin = it },
            label = { Text("PIN") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = problem,
            onValueChange = { problem = it },
            label = { Text("Problem Description") },
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = attachmentId,
            onValueChange = { attachmentId = it },
            label = { Text("Attachment ID (optional)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.submitComplaint(
                    fullname = fullname,
                    email = email,
                    phone = phone,
                    pin = pin,
                    problem = problem,
                    attachmentId = attachmentId
                )
            },
            enabled = !viewModel.isSubmitting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Complaint")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.isSubmitting) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.submitMessage)
    }
}
