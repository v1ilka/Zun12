package com.example.zun.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class GenderSpinnerAdapter extends ArrayAdapter<String> {
    public GenderSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }
}
