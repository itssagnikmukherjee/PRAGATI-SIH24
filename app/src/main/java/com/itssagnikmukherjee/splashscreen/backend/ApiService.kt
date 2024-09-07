package com.itssagnikmukherjee.splashscreen.backend

//import com.itssagnikmukherjee.splashscreen.backend.ApiClient.retrofit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @GET("complaint")
    suspend fun getComplaints():  Response<Map<String, List<Complaint>>>

//    @POST("complaints")
//    suspend fun createComplaint(@Body complaint: Complaint): Response<Complaint>
//
//    @GET("contractors")
//    suspend fun getContractors(): Response<List<Contractor>>
//
//    @POST("contractors")
//    suspend fun createContractor(@Body contractor: Contractor): Response<Contractor>

    @GET("departmentals")
    suspend fun getDepartmentals(): Response<List<Departmental>>

    @POST("departmentals")
    suspend fun createDepartmental(@Body departmental: Departmental): Response<Departmental>

    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>

//    @POST("tasks")
//    suspend fun createTask(@Body task: Task): Response<Task>
}