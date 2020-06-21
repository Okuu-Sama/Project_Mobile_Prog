package com.example.project_nicolas_jatob.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_nicolas_jatob.Constants;
import com.example.project_nicolas_jatob.DetailActivity;
import com.example.project_nicolas_jatob.R;
import com.example.project_nicolas_jatob.data.GranblueApi;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.example.project_nicolas_jatob.presentation.model.RestGranblueResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Okuu-Sama/Project_Mobile_Prog/master/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    public static Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("mobile_project", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Granblue_Character> characterList = getDataFromCache();

        if(characterList != null)
        {
            showList(characterList);
        }else
        {
            makeApiCall();
        }

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

    private void showList(List<Granblue_Character> granblueList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        // define an adapter
        mAdapter = new ListAdapter(granblueList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Granblue_Character item) {
                navigateToDetails(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GranblueApi granblueApi = retrofit.create(GranblueApi.class);

        Call<RestGranblueResponse> call = granblueApi.getGranblueCharacter();
        call.enqueue(new Callback<RestGranblueResponse>() {
            @Override
            public void onResponse(Call<RestGranblueResponse> call, Response<RestGranblueResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Granblue_Character> characterList = response.body().getResults();
                    saveList(characterList);
                    showList(characterList);
                }else
                {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestGranblueResponse> call, Throwable t) {
                showError();
            }
        });

    }

    private void saveList(List<Granblue_Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GRANBLUE_CHARACTER_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails (Granblue_Character character)
    {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("characterKey", gson.toJson(character));
        MainActivity.this.startActivity(myIntent);
    }
}
