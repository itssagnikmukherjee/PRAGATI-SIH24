package com.itssagnikmukherjee.splashscreen.backend

data class Complaint(
    val _id:String,
    val fullname: String,
    val email: String,
    val phone: String,
    val pin: String,
    val problem: String,
    val attachment_id: String? = null,
    var isForwarded: Boolean = false
)

// Contractor.kt
data class Contractor(
    val name: String,
    val phone: String,
    val email: String,
    val locality: Locality,
    val pincode: String,
    val issue_id_file_id: String,
    val license_file_id: String,
    val verified: Boolean = false,
    val password: String,
    val user_id: String? = null
)

data class Locality(
    val type: String = "Point",
    val coordinates: List<Double> // List of [longitude, latitude]
)

// Departmental.kt
data class Departmental(
    val name: String,
    val pass: String,
    val email: String,
    val phone: String,
    val dept_level: String,
    val role: String,       // Should be either "Technical" or "Head"
    val emp_no: String,
    val location: String,
    val office_address: String,
    val pin_code: String,
    val govt_issue_id_file_id: String,
    val authorisation_file_id: String,
    val prev_project_file_id: String? = null,
    val verified: Boolean = false,
    val user_id: String? = null
)

// Task.kt
data class Task(
    val _id: String,
    val locality: String,
    val work_type: String,
    val department_level: String,
    val start_date: String,
    val end_date: String,
    val head_ids: List<String>,
    val resources: List<String>,
)
