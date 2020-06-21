package com.example.project_nicolas_jatob.presentation.controller;

import android.content.Intent;

import com.example.project_nicolas_jatob.Singletons;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.example.project_nicolas_jatob.presentation.view.DetailActivity;

public class DetailController {

    private DetailActivity view;

    public void onStart(Intent detailIntent)
    {
        String characterJson = detailIntent.getStringExtra("characterKey");
        Granblue_Character character = Singletons.getGson().fromJson(characterJson,Granblue_Character.class);
        view.showDetail(character);
    }
}
