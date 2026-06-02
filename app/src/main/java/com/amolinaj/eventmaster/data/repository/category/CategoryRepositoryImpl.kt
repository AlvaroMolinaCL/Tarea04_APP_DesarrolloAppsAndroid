package com.amolinaj.eventmaster.data.repository.category

import android.util.Log
import com.amolinaj.eventmaster.data.remote.dto.CategoryDto
import com.amolinaj.eventmaster.data.remote.service.EventMasterApiService
import com.amolinaj.eventmaster.ui.model.EventCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: EventMasterApiService
) : CategoryRepository {

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val categories = MutableStateFlow<List<EventCategory>>(emptyList())

    init {
        refreshCategories()
    }

    override fun observeAll(): Flow<List<EventCategory>> {
        return categories.asStateFlow()
    }

    override suspend fun insert(name: String, description: String) {
        try {
            apiService.createCategory(
                CategoryDto(
                    name = name,
                    description = description
                )
            )
            refreshCategories()
        } catch (exception: Exception) {
            Log.e("CategoryRepository", "No se pudo crear la categoria", exception)
        }
    }

    private fun refreshCategories() {
        repositoryScope.launch {
            try {
                categories.value = apiService.getCategories().map { it.toModel() }
            } catch (exception: Exception) {
                Log.e("CategoryRepository", "No se pudieron cargar las categorias", exception)
            }
        }
    }

    private fun CategoryDto.toModel(): EventCategory {
        return EventCategory(
            id = id ?: 0,
            name = name,
            description = description
        )
    }
}
