import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itssagnikmukherjee.splashscreen.backend.Complaint
import com.itssagnikmukherjee.splashscreen.backend.ComplaintViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class MyActivity : AppCompatActivity() {

    private val viewModel: ComplaintViewModel by viewModels()

    private fun submitComplaint() {
        lifecycleScope.launch {
            viewModel.submitComplaint(
                fullname = "John Doe",
                email = "john.doe@example.com",
                phone = "1234567890",
                pin = "12345",
                problem = "Test complaint",
                location = "Test location",
                attachmentId = "attachment123"
            )
        }
    }
}

suspend fun fetchComplaints(): List<Complaint>? {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.0.7:3000/api/complaint")
            .get()
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", "MyApp")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                println("Error: ${response.code} - ${response.message}")
                response.body?.string()?.let { println("Response Body: $it") }
                null
            } else {
                val jsonResponse = response.body?.string()
                println("Success: $jsonResponse")
                jsonResponse?.let {
                    // Convert JSON response to List<Complaint>
                    parseComplaints(it)
                }
            }
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
            null
        }
    }
}

private fun parseComplaints(jsonResponse: String): List<Complaint>? {
    return try {
        val gson = Gson()
        val type = object : TypeToken<List<Complaint>>() {}.type
        gson.fromJson(jsonResponse, type)
    } catch (e: Exception) {
        println("Parsing error: ${e.message}")
        null
    }
}

suspend fun sendComplaint(
    fullname: String,
    email: String,
    phone: String,
    location: String,
    pin: String,
    problem: String,
    attachmentId: String
): Response? {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()
        val jsonBody = """
            {
                "fullname": "$fullname",
                "email": "$email",
                "phone": "$phone",
                "location": "$location",
                "pin": "$pin",
                "problem": "$problem",
                "attachment_id": "$attachmentId"
            }
        """.trimIndent()

        val body = RequestBody.create(mediaType, jsonBody)
        val request = Request.Builder()
            .url("http://192.168.0.7:3000/api/complaint")
            .post(body)
            .addHeader("Accept", "*/*")
            .addHeader("User-Agent", "Thunder Client (https://www.thunderclient.com)")
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                println("Error: ${response.code} - ${response.message}")
                response.body?.string()?.let { println("Response Body: $it") }
            } else {
                println("Success: ${response.body?.string()}")
            }
            response
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
            null
        }
    }
}