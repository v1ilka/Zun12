package com.example.zun.hardcoded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.zun.R;

import java.util.ArrayList;

public class ThemesListShowActivity extends AppCompatActivity {
    private ListView themeslist123;
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_themes_list_show);
        themeslist123 = findViewById(R.id.themeslist);
        back = findViewById(R.id.back);
        ThemesTable connection = new ThemesTable(this);
        int man = getIntent().getIntExtra("man",2);
        ArrayList<Theme> themes = connection.selectAll(man);
        ArrayAdapter<Theme> themeArrayAdapter = new ArrayAdapter<>(ThemesListShowActivity.this, android.R.layout.simple_list_item_1, themes);
        themeslist123.setAdapter(themeArrayAdapter);
        themeslist123.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Theme theme = themeArrayAdapter.getItem(i);
                Intent intent = (new Intent(ThemesListShowActivity.this, ThemeShowActivity.class));
                intent.putExtra("theme", theme.getTheme());
                intent.putExtra("story", theme.getStory());
                startActivity(intent);
            }
        });
        themeslist123.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Theme te = themeArrayAdapter.getItem(i);

                connection.delete(te.getId());
                themes.clear();
                themes.addAll(connection.selectAll(man));
                themeArrayAdapter.notifyDataSetInvalidated();
                return true;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ThemesListShowActivity.this, ChoiceActivity.class));
            }
        });
    }
}
