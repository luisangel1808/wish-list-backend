package com.carvajal.whishlist.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String Username;
    @NotBlank
    private String password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
