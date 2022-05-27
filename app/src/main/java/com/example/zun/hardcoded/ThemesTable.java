package com.example.zun.hardcoded;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class ThemesTable {
    private Context context;
    private AppDataBase connection;

    public ThemesTable(Context context) {
        this.context = context;
        connection = new AppDataBase(context);
    }

    public void insert(Theme themes) {
        ContentValues cv = new ContentValues();
        cv.put("theme", themes.getTheme());
        cv.put("man", themes.getMan());
        cv.put("story", themes.getStory());
        connection.getWritableDatabase().insert("themes", null, cv);
    }


    public ArrayList<Theme> selectAll(int man) {
        ArrayList<Theme> list = new ArrayList<>();
        Cursor c;
        if (man == -1) {
            c = connection.getWritableDatabase().query("themes", null, null, null, null, null, null);
        } else {
            c = connection.getWritableDatabase().query("themes", null, "man =? or man = 2", new String[]{String.valueOf(man)}, null, null, null);
        }
        c.moveToFirst();
        if (!c.isAfterLast()) {
            do {
                Theme t = new Theme();
                t.setTheme(c.getString(1));
                t.setMan(c.getInt(2));
                t.setStory(c.getString(3));
                t.setId(c.getInt(0));
                list.add(t);
            } while (c.moveToNext());
        }
        return list;
    }

    public  void delete(int id){
        String[] args = {id+ ""};
        connection.getWritableDatabase().delete("themes","id = ?",args);

    }
}
