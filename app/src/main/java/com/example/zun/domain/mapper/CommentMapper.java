package com.example.zun.domain.mapper;

import com.example.zun.domain.Comment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {
    public Comment commentFromThemeJsonArray(JSONObject jsonObject){
        Comment comment = null;
        try {
             comment = new Comment(
            jsonObject.getInt("id"),
            jsonObject.getString("context"),
            0
             );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return comment;
    }
}
