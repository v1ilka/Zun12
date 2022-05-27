package com.example.zun.hardcoded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zun.R;


public class ChoiceActivity extends AppCompatActivity {
private ImageView malechoice,femalechoice,admin_button,favorite;
    private Button btnAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choice);

        malechoice = findViewById(R.id.malechoice);
        femalechoice = findViewById(R.id.femalechoice1);
        admin_button = findViewById(R.id.admin);
        btnAll = findViewById(R.id.btn_all);
        favorite = findViewById(R.id.favor);
        malechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoiceActivity.this, ThemesListShowActivity.class);
                i.putExtra("man",1);
                startActivity(i);
            }
        });
        femalechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ChoiceActivity.this, ThemesListShowActivity.class);
                a.putExtra("man",0);

                startActivity(a);
            }
        });
        admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceActivity.this,AdminActivity.class));
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceActivity.this,FavoriteShowActivity.class));
            }
        });
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoiceActivity.this,ThemesListShowActivity.class));
            }
        });
    }
}