package com.example.zun.domain;

import java.util.List;
import java.util.Objects;

public class Theme {
    private int id;

    private  String name;

    private int gender_id;

    private String content;
    private User user;
    private List<Comment> commentList;

    private int user_id;

    private int status;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender_id() {
        return gender_id;
    }

    public String getContent() {
        return content;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getStatus() {
        return status;
    }

    public Theme(int id, String name, int gender_id, String content, int user_id, int status) {
        this.id = id;
        this.name = name;
        this.gender_id = gender_id;
        this.content = content;
        this.user_id = user_id;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender_id=" + gender_id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", commentList=" + commentList +
                ", user_id=" + user_id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return id == theme.id && gender_id == theme.gender_id && user_id == theme.user_id && status == theme.status && name.equals(theme.name) && content.equals(theme.content) && user.equals(theme.user) && commentList.equals(theme.commentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender_id, content, user, commentList, user_id, status);
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public Theme(int id, String name, int gender_id, String content, User user, List<Comment> commentList, int user_id, int status) {
        this.id = id;
        this.name = name;
        this.gender_id = gender_id;
        this.content = content;
        this.user = user;
        this.commentList = commentList;
        this.user_id = user_id;
        this.status = status;
    }
}
