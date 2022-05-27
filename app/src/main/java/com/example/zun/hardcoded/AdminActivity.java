package com.example.zun.hardcoded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zun.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText theme, story;
    private ImageView add, back;
    private CheckBox mchoice, fchoice;
    private ThemesTable polTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin);
        mchoice = findViewById(R.id.mchoice);
        fchoice = findViewById(R.id.fchoice);
        theme = findViewById(R.id.theme);
        story = findViewById(R.id.story);
        add = findViewById(R.id.add);
        back = findViewById(R.id.back);
        add.setOnClickListener(this);
        polTable = new ThemesTable(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ChoiceActivity.class));
            }
        });
    }


    @Override
    public void onClick(View view) {
        String t, s;
        t = theme.getText().toString();
        s = story.getText().toString();

        if (t.equals("") || s.equals("")) {
            Toast.makeText(this,
                    "Пожалуйста введите данные во все поля.", Toast.LENGTH_SHORT).show();
            return;
        }

        Theme themes = new Theme();
        themes.setTheme(t);
        themes.setStory(s);
        boolean male = mchoice.isChecked();
        boolean female = fchoice.isChecked();
        if (male && female) {
            themes.setMan(2);
        } else if (male) {
            themes.setMan(1);
        } else if (female) {
            themes.setMan(0);

        } else {
            Toast.makeText(this, "Пожалуйста выберите пол", Toast.LENGTH_SHORT).show();
            return;
        }

        theme.setText("");
        story.setText("");
        polTable.insert(themes);
        startActivity(new Intent(AdminActivity.this, ThemesListShowActivity.class));
    }
}