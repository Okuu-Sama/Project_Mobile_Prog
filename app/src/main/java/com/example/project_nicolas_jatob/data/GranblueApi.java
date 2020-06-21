package com.example.project_nicolas_jatob.data;

import com.example.project_nicolas_jatob.presentation.model.RestGranblueResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GranblueApi {
    @GET("GranblueFantasyapi.json")
    Call<RestGranblueResponse> getGranblueCharacter();
}
