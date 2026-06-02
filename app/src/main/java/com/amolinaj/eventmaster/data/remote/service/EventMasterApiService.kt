package com.amolinaj.eventmaster.data.remote.service

import com.amolinaj.eventmaster.data.remote.dto.CategoryDto
import com.amolinaj.eventmaster.data.remote.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventMasterApiService {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @POST("categories")
    suspend fun createCategory(@Body category: CategoryDto): CategoryDto

    @GET("events")
    suspend fun getEvents(): List<EventDto>

    @GET("events/{id}")
    suspend fun getEventById(@Path("id") id: Int): EventDto

    @POST("events")
    suspend fun createEvent(@Body event: EventDto): EventDto
}
