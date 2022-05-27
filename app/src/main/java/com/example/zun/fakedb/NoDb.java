package com.example.zun.fakedb;

import com.example.zun.domain.Comment;
import com.example.zun.domain.Theme;
import com.example.zun.domain.User;

import java.util.ArrayList;
import java.util.List;

public class NoDb {
    public static final  String[] GENDER_LIST = {"man", "woman", "all"};

    private NoDb(){}

    public static  final List<Theme> THEME_LIST = new ArrayList<>();
    public static  final List<User> USER_LIST = new ArrayList<>();
    public static  final List<Comment> COMMENT_LIST = new ArrayList<>();

}
