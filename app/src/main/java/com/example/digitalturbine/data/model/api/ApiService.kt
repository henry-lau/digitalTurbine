package com.example.digitalturbine.data.model.api

import com.example.digitalturbine.data.model.db.data.ads
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getAds?id=236&password=OVUJ1DJN&&siteId=10777&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10")
    suspend fun getAdList(
        @Query("lname") lat: String
    ): ads
}
