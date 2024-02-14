package com.ayiko.backend.model;

import com.ayiko.backend.enums.Gender;
import com.ayiko.backend.enums.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "ayiko_users")
@Getter
@Setter
public class User {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private @Id
    @GeneratedValue
    long id;
    private @NotNull String name;
    private @NotNull String email;
    private String mobileNumber;
    private @NotNull String password;
    @ApiModelProperty(hidden = true)
    private Gender gender;
    private Date birthDate;
    @ApiModelProperty(hidden = true)
    private String country;

    @ApiModelProperty(hidden = true)
    private Boolean active;
    @ApiModelProperty(hidden = true)
    private Boolean verified;
    @ApiModelProperty(hidden = true)
    private String firebaseToken;
    private String countryCode;
    private Date signupDate;
    @ApiModelProperty(hidden = true)
    private Date lastLoginDate;
    @ApiModelProperty(hidden = true)
    private UserType userType;

    @Lob
    @ApiModelProperty(hidden = true)
    private byte[] profileImage;

    @ApiModelProperty(hidden = true)
    @Column(nullable = true)
    private Integer firebaseUserId;

    public User() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result ;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }

        return true;
    }
}