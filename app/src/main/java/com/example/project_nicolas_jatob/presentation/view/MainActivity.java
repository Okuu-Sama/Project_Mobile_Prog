package com.example.project_nicolas_jatob.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_nicolas_jatob.DetailActivity;
import com.example.project_nicolas_jatob.R;
import com.example.project_nicolas_jatob.Singletons;
import com.example.project_nicolas_jatob.presentation.controller.MainController;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
               Singletons.getSharedPreferences(getApplicationContext())
        );
        controller.onStart();
    }

    public void showList(List<Granblue_Character> granblueList) {
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

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails (Granblue_Character character)
    {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("characterKey", Singletons.getGson().toJson(character));
        MainActivity.this.startActivity(myIntent);
    }
}
