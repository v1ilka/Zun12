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

public class FavoriteShowActivity extends AppCompatActivity {
private ImageView back0;
private ListView favoriteslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_favorite_show);
        back0 = findViewById(R.id.back);
        favoriteslist = findViewById(R.id.favoriteslist);
        back0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FavoriteShowActivity.this, ChoiceActivity.class);
                startActivity(i);
            }
        });
        ThemesTable connection = new ThemesTable(this);
        ArrayList<Theme> themes = connection.selectAll(-1);
        ArrayAdapter<Theme> favoritethemeArrayAdapter = new ArrayAdapter<>(FavoriteShowActivity.this, android.R.layout.simple_list_item_1,themes);
       favoriteslist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Theme te = favoritethemeArrayAdapter.getItem(i);
                connection.delete(te.getId());
                themes.clear();
                themes.addAll(connection.selectAll(-1));
                favoritethemeArrayAdapter.notifyDataSetInvalidated();
                return true;
            }
        });

    }
}