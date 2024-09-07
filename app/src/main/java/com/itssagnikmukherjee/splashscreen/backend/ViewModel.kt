package com.itssagnikmukherjee.splashscreen.backend
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import java.io.IOException
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itssagnikmukherjee.splashscreen.backend.ApiClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
//import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sendComplaint
import java.net.HttpURLConnection
import java.net.URL
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import okhttp3.Response



class ComplaintViewModel : ViewModel() {
    var isSubmitting by mutableStateOf(false)
    var submitMessage by mutableStateOf("")

    private val _complaints = MutableStateFlow<List<Complaint>>(emptyList())
    val complaints: StateFlow<List<Complaint>> = _complaints.asStateFlow()

    val forwardedComplaints: StateFlow<List<Complaint>> = _complaints.map { it.filter { it.isForwarded } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

//FETCHING TASKS


    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    val tasks: State<List<Task>> = _tasks

    fun fetchTasks() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.0.7:3000/api/tasks")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { responseBody ->
                    val json = responseBody.string()
                    val tasks = parseTasks(json)
                    _tasks.value = tasks
                }
            }
        })
    }

    private fun parseTasks(json: String): List<Task> {
        val tasks = mutableListOf<Task>()
        val jsonArray = JSONObject(json).getJSONArray("tasks")

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            // Extract head_ids
            val headIdsArray = jsonObject.getJSONArray("head_ids")
            val headIds = List(headIdsArray.length()) { j -> headIdsArray.getString(j) }

            // Extract resources
            val resourcesArray = jsonObject.getJSONArray("resources")
            val resources = List(resourcesArray.length()) { j -> resourcesArray.getString(j) }

            val task = Task(
                _id = jsonObject.getString("_id"),
                locality = jsonObject.getString("locality"),
                work_type = jsonObject.getString("work_type"),
                department_level = jsonObject.getString("department_level"),
                start_date = jsonObject.getString("start_date"),
                end_date = jsonObject.getString("end_date"),
                head_ids = headIds,
                resources = resources,
            )
            tasks.add(task)
        }
        return tasks
    }


//

    fun fetchComplaints() {
        viewModelScope.launch {
            try {
                val response = apiService.getComplaints()
                if (response.isSuccessful) {
                    _complaints.value = response.body()?.get("data") ?: emptyList()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }



    fun submitComplaint(
        fullname: String,
        email: String,
        phone: String,
        location: String,
        pin: String,
        problem: String,
        attachmentId: String
    ) {
        viewModelScope.launch {
            isSubmitting = true
            submitMessage = ""

            // Call the sendComplaint function
            val response = sendComplaint(
                fullname = fullname,
                email = email,
                phone = phone,
                location = location,
                pin = pin,
                problem = problem,
                attachmentId = attachmentId
            )
            // Update the UI based on response
            if (response?.isSuccessful == true) {
                submitMessage = "Complaint successfully submitted!"
            } else {

                submitMessage = "Failed to submit complaint. Please try again."
            }
            isSubmitting = false
        }
    }
}

class SharedComplaintViewModel : ViewModel() {
    // Mutable state flow to hold forwarded complaints
    private val _forwardedComplaints = MutableStateFlow<List<Complaint>>(emptyList())
    val forwardedComplaints: StateFlow<List<Complaint>> = _forwardedComplaints

    // Function to add a complaint to the forwarded list
    fun forwardComplaint(complaint: Complaint) {
        viewModelScope.launch {
            _forwardedComplaints.value = _forwardedComplaints.value + complaint
        }
    }
}


object ApiClient {
    private const val BASE_URL = "http://192.168.0.7:3000/api/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.0.7:3000/api/tasks")
                    .build()

                client.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        // Handle error
                    }

                    override fun onResponse(call: okhttp3.Call, response: Response) {
                        response.body?.let { responseBody ->
                            val json = responseBody.string()
                            val tasks = parseTasks(json)
                            _tasks.value = tasks
                        }
                    }
                })
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    private fun parseTasks(json: String): List<Task> {
        val tasks = mutableListOf<Task>()
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("tasks")

        for (i in 0 until jsonArray.length()) {
            val jsonTask = jsonArray.getJSONObject(i)
            tasks.add(
                Task(
                    _id =  jsonTask.getString("_id"),
                    locality = jsonTask.getString("locality"),
                    work_type = jsonTask.getString("work_type"),
                    department_level = jsonTask.getString("department_level"),
                    start_date =  jsonTask.getString("start_date"),
                    end_date = jsonTask.getString("end_date"),
                    head_ids = jsonTask.getJSONArray("head_ids").let {
                        List(it.length()) { index -> it.getString(index) }
                    },
                    resources = jsonTask.getJSONArray("resources").let {
                        List(it.length()) { index -> it.getString(index) }
                    }
                )
            )
        }
        return tasks
    }
}