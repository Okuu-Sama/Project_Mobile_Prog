package com.example.project_nicolas_jatob;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project_nicolas_jatob.Constants;
import com.example.project_nicolas_jatob.data.GranblueApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static GranblueApi granblueApiInstance;
    private static SharedPreferences sharedPreferencesInstance;


    public static Gson getGson()
    {
        if(gsonInstance== null)
        {
            gsonInstance = new GsonBuilder().setLenient().create();
        }
        return gsonInstance;
    }

    public static GranblueApi getGranblueApi()
    {
        if(granblueApiInstance == null)
        {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            granblueApiInstance = retrofit.create(GranblueApi.class);
        }
        return granblueApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context)
    {
        if(sharedPreferencesInstance== null)
        {
            sharedPreferencesInstance = context.getSharedPreferences("mobile_project", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
