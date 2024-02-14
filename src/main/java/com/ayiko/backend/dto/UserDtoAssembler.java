package com.ayiko.backend.dto;

import com.ayiko.backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoAssembler {


    public UserDto doAssemble(User user, boolean assembleLeagues, boolean adminCall) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setActive(user.getActive());
        dto.setBirthDate(user.getBirthDate());
        dto.setCountry(user.getCountry());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setGender(user.getGender());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setCountryCode(user.getCountryCode());

        if (user.getProfileImage() != null && !adminCall) {
            dto.setProfileImage(new String(user.getProfileImage()));
        }
        return dto;
    }
}
