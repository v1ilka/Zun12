package com.example.zun.domain.mapper;

import com.example.zun.domain.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserMapper {
    public User userFromThemeJsonArray(JSONObject jsonObject){
        User user = null;
        try {

            user = new User(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("gender_id"),
                    jsonObject.getString("email"),
                    "",
                    jsonObject.getInt("role")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return user;
    }

}
