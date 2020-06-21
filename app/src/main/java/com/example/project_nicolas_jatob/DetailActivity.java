package com.example.project_nicolas_jatob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_nicolas_jatob.presentation.model.Granblue_Character;
import com.example.project_nicolas_jatob.presentation.view.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
        String characterJson = intent.getStringExtra("characterKey");
        Granblue_Character character = MainActivity.gson.fromJson(characterJson,Granblue_Character.class);
        showDetail(character);
    }

    private void showDetail(Granblue_Character character) {
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
    }
}
