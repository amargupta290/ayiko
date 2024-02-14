package com.ayiko.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VerifyOtpDto {
    private @NotNull Long userId;
    private @NotNull String otp;
}
