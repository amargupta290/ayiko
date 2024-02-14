package com.ayiko.backend.dto;

import com.ayiko.backend.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long id;
    private @NotNull String name;
    private @NotNull String email;
    private String mobileNumber;
    private @NotNull String password;
    private Gender gender;
    private Date birthDate;
    private String country;
    private Boolean active;
    private Integer interestTeamId;
    private String profileImage;
    private Integer teamId;
    private String countryCode;
    private String leagueJoinStatus;
    private BigDecimal empireCoins;
    private String bonusStatus;
    private Integer firebaseUserId;

}
