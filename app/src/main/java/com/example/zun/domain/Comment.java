package com.example.zun.domain;

import java.util.Objects;

public class Comment {
    private  int id;

    private String context;

    private int theme_id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && theme_id == comment.theme_id && context.equals(comment.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, context, theme_id);
    }

    @Override
    public String toString() {
        return ""+ ""+ context;
    }

    public String getContext() {
        return context;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public Comment(int id, String context, int theme_id) {
        this.id = id;
        this.context = context;
        this.theme_id = theme_id;
    }
}
