package com.carvajal.whishlist.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class NewUser {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
    private LocalDate birthDate;
    private int identificationType;
    private String identification;
    private Set<String> roles = new HashSet<>();
}
