package com.ayiko.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_otp")
@Getter
@Setter
public class UserOtp {
    private @Id
    @GeneratedValue
    Integer id;
    private Long userId;
    private String otp;
    private Boolean verified;
    private Date expirationDate;
}
