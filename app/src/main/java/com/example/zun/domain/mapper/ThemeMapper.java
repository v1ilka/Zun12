package com.example.zun.domain.mapper;

import com.example.zun.domain.Comment;
import com.example.zun.domain.Theme;
import com.example.zun.domain.User;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ThemeMapper {

    public Theme themeFromJsonArray(JSONObject jsonObject, User user, List<Comment> commentList){
        Theme theme = null;
        try {
            theme = new Theme(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("gender_id"),
                    jsonObject.getString("content"),
                    user,commentList,
                    user.getId(),
                    jsonObject.getInt("status")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return theme;
    }
    }

