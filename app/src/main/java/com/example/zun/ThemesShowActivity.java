package com.example.zun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zun.hardcoded.AdminActivity;
import com.example.zun.hardcoded.ChoiceActivity;

public class ThemesShowActivity extends AppCompatActivity {
private TextView title, content_of_title;
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_show);
        back = findViewById(R.id.back);
        title = findViewById(R.id.theme_title);
        content_of_title = findViewById(R.id.storytext);
        Intent intent = getIntent();
        String tt = intent.getStringExtra("name");
        String st = intent.getStringExtra("content");
        title.setText(tt);
        content_of_title.setText(st);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemesShowActivity.this, MainActivity.class));
            }
        });
    }
}