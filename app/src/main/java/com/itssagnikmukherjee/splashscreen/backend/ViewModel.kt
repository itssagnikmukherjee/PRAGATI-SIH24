package com.itssagnikmukherjee.splashscreen.backend

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itssagnikmukherjee.splashscreen.backend.ApiClient.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sendComplaint

class ComplaintViewModel : ViewModel() {
    var isSubmitting by mutableStateOf(false)
    var submitMessage by mutableStateOf("")

    private val _complaints = MutableStateFlow<List<Complaint>>(emptyList())
    val complaints: StateFlow<List<Complaint>> = _complaints.asStateFlow()

    val forwardedComplaints: StateFlow<List<Complaint>> = _complaints.map { it.filter { it.isForwarded } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())



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