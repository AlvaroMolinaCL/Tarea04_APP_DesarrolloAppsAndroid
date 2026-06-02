package com.amolinaj.eventmaster.data.repository.event

import android.util.Log
import com.amolinaj.eventmaster.data.remote.dto.EventDto
import com.amolinaj.eventmaster.data.remote.service.EventMasterApiService
import com.amolinaj.eventmaster.ui.model.EventItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val apiService: EventMasterApiService
) : EventRepository {

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val events = MutableStateFlow<List<EventItem>>(emptyList())

    init {
        refreshEvents()
    }

    override fun observeAll(): Flow<List<EventItem>> {
        return events.asStateFlow()
    }

    override suspend fun insert(
        categoryId: Int,
        title: String,
        description: String,
        date: String,
        location: String,
        imageResName: String?
    ) {
        try {
            apiService.createEvent(
                EventDto(
                    categoryId = categoryId,
                    title = title,
                    description = description,
                    date = date,
                    location = location,
                    imageResName = imageResName
                )
            )
            refreshEvents()
        } catch (exception: Exception) {
            Log.e("EventRepository", "No se pudo crear el evento", exception)
        }
    }

    private fun refreshEvents() {
        repositoryScope.launch {
            try {
                events.value = apiService.getEvents().map { it.toModel() }
            } catch (exception: Exception) {
                Log.e("EventRepository", "No se pudieron cargar los eventos", exception)
            }
        }
    }

    private fun EventDto.toModel(): EventItem {
        return EventItem(
            id = id ?: 0,
            categoryId = categoryId,
            title = title,
            description = description,
            date = date,
            location = location,
            imageResName = imageResName
        )
    }
}
