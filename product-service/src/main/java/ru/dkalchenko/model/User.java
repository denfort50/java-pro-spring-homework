package ru.dkalchenko.model;

import java.util.Objects;

public class User {

    private Long id;
    private String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (User) obj;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User["
                + "id=" + id + ", "
                + "username=" + username + ']';
    }

}
