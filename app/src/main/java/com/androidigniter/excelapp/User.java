package com.androidigniter.excelapp;

import java.util.Date;

/**
 * Created by AndroidIgniter on 23 Mar 2019 020.
 */

public class User {
    String email;
    String name;
    String token;
    String role;
    Date sessionExpiryDate;

    public void setEmail(String username) {
        this.email = username;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
