package com.ayiko.backend.util;

import com.ayiko.backend.exception.ResourceNotFoundException;
import com.ayiko.backend.model.User;
import com.ayiko.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtTokenUtil;

    public User findUserByUserName(String userName) {
        try {
            if (userName.indexOf("@") != -1) {
                return userRepository.findByEmail(userName);
            }
            return userRepository.findByMobileNumber(userName);
        } catch (Exception exception) {
            throw new ResourceNotFoundException("User details not found or User is inactive.");
        }
    }

    public User extractUser(String jwtToken) {
        String userName = jwtTokenUtil.extractUsername(jwtToken.substring(7));
        return findUserByUserName(userName);
    }

}
