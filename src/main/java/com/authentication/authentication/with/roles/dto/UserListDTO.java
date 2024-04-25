package com.authentication.authentication.with.roles.dto;

import com.authentication.authentication.with.roles.models.Role;
import jakarta.persistence.Column;

import java.util.Set;

public class UserListDTO {

    private Integer userId;
    private String username;
    private String password;
    private Set<Role> authorities;
    

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
