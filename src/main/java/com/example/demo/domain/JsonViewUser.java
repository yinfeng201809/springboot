package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonView;

public class JsonViewUser {

    public interface WithoutPasswordView {};
    public interface WithPasswordView extends WithoutPasswordView {};

    private String username;
    private String password;

    public JsonViewUser() {
    }

    public JsonViewUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return this.password;
    }
}
