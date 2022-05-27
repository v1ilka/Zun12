package com.example.zun.hardcoded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zun.R;


public class ThemeShowActivity extends AppCompatActivity {
private TextView them, stor;
public ImageView back0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_show);
        getSupportActionBar().hide();
        them = findViewById(R.id.themetext);
        back0 = findViewById(R.id.back);
        stor = findViewById(R.id.storytext);
        Intent intent = getIntent();
        String th =  intent.getStringExtra("theme");
        String st = intent.getStringExtra("story");
        them.setText(th);
        stor.setText(st);
        back0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThemeShowActivity.this, ChoiceActivity.class);
                startActivity(i);
            }
        });
    }
}
