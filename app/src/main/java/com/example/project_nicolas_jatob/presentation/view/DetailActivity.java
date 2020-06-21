package com.example.project_nicolas_jatob.presentation.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.project_nicolas_jatob.R;
import com.example.project_nicolas_jatob.Singletons;
import com.example.project_nicolas_jatob.presentation.controller.DetailController;
import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private ImageView characterImage;
    private TextView txtName;
    private TextView txtAtk;
    private TextView txtHp;
    private TextView txtElement;
    private TextView txtRace;
    private TextView txtStyle;
    private TextView txtSpecialty;
    private TextView txtGender;
    private TextView txtVoice;

    private DetailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        layout = findViewById(R.id.detailLayout);
        txtName = findViewById(R.id.charactername_txt);
        txtAtk = findViewById(R.id.characteratk_txt);
        txtHp = findViewById(R.id.characterhp_txt);
        characterImage = findViewById(R.id.imageView2);
        txtElement = findViewById(R.id.characterelement_txt);
        txtRace = findViewById(R.id.characterrace_txt);
        txtStyle = findViewById(R.id.characterstyle_txt);
        txtSpecialty = findViewById(R.id.characterspecialty_txt);
        txtGender = findViewById(R.id.charactergender_txt);
        txtVoice = findViewById(R.id.charactervoice_txt);
        
        Intent intent = getIntent();
        controller.onStart(intent);
    }

    public void showDetail(Granblue_Character character) {
        Picasso.get().load(character.getImgUrl()).into(characterImage);
        txtName.setText(character.getName());
        txtAtk.setText(String.valueOf(character.getMaxATK()));
        txtHp.setText(String.valueOf(character.getMaxHP()));
        txtElement.setText(character.getElement());
        txtRace.setText(character.getRace());
        txtStyle.setText(character.getStyle());
        txtSpecialty.setText(character.getSpecialty());
        txtGender.setText(character.getGender());
        txtVoice.setText(character.getVoice_actor());

        int color = Color.WHITE;
        switch(character.getElement())
        {
            case "Dark":
                color = getResources().getColor(R.color.colorDarkElement);
                break;
            case "Light":
                color = getResources().getColor(R.color.colorLightElement);
                break;
            case "Fire":
                color = getResources().getColor(R.color.colorFireElement);
                break;
            case "Water":
                color = getResources().getColor(R.color.colorWaterElement);
                break;
            case "Earth":
                color = getResources().getColor(R.color.colorEarthElement);
                break;
            case "Wind":
                color = getResources().getColor(R.color.colorWindElement);
                break;
        }
        layout.setBackgroundColor(color);
    }
}
