package com.example.zun.hardcoded;

import java.io.Serializable;

public class Theme implements Serializable {
    private String theme,story;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMan() {
        return man;
    }

    public void setMan(Integer man) {
        this.man = man;
    }

    private Integer man;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Theme(String theme, String story) {
        this(theme, story, null);
    }

    @Override
    public String toString() {
        return "" +
                "" + theme  ;
    }
    public Theme(){}

    public Theme(String theme, String story, Integer man) {
        this.theme = theme;
        this.story = story;
        this.man = man;
    }
}
