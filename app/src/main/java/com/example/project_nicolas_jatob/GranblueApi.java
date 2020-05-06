package com.example.project_nicolas_jatob;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GranblueApi {
    @GET("GranblueFantasyapi.json")
    Call<RestGranblueResponse> getGranblueCharacter();
}
