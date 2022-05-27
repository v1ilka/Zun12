package com.example.zun.rest;

import com.example.zun.domain.Theme;

public interface ZunApi {
    void fillTheme();

    void fillUser();

    void fillComment();

    void newTheme(Theme theme);

    void updateTheme(int id, String newThemeName,String newUserName, String newCommentName);

    void deleteTheme(int id);
}
