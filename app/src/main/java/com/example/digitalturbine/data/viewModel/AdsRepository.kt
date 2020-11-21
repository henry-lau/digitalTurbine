package com.example.digitalturbine.data.viewModel

import com.example.digitalturbine.data.model.api.ApiService
import com.example.digitalturbine.data.model.db.data.ads
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface Repository {
    suspend fun fetchList(lname: String): ads
}

open class AdsRepository constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun fetchList(lname: String): ads {
        return withContext(dispatcher) {
            apiService.getAdList(lname)
        }
    }
}