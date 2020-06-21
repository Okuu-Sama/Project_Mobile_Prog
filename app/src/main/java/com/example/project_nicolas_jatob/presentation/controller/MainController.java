package com.example.project_nicolas_jatob.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.project_nicolas_jatob.Constants;
import com.example.project_nicolas_jatob.Singletons;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.example.project_nicolas_jatob.presentation.model.RestGranblueResponse;
import com.example.project_nicolas_jatob.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart()
    {
        List<Granblue_Character> characterList = getDataFromCache();

        if(characterList != null)
        {
            view.showList(characterList);
        }else
        {
            makeApiCall();
        }
    }

    private void makeApiCall()
    {
        Call<RestGranblueResponse> call = Singletons.getGranblueApi().getGranblueCharacter();
        call.enqueue(new Callback<RestGranblueResponse>() {
            @Override
            public void onResponse(Call<RestGranblueResponse> call, Response<RestGranblueResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Granblue_Character> characterList = response.body().getResults();
                    saveList(characterList);
                    view.showList(characterList);
                }else
                {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestGranblueResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void saveList(List<Granblue_Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GRANBLUE_CHARACTER_LIST, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();

    }

    private List<Granblue_Character> getDataFromCache() {
        String jsonGranblue = sharedPreferences.getString(Constants.KEY_GRANBLUE_CHARACTER_LIST,null);

        if(jsonGranblue == null)
        {
            return null;
        }else {
            Type listType = new TypeToken<List<Granblue_Character>>() {}.getType();
            return gson.fromJson(jsonGranblue, listType);
        }
    }

    public void onItemClick(Granblue_Character character)
    {

    }

    public void onButtonAClick()
    {

    }
}
