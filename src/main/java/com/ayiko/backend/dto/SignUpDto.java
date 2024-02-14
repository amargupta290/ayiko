package com.ayiko.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {
    private @NotNull String name;
    private @NotNull String email;
    private @NotNull String phoneNumber;
    private @NotNull String password;
}
