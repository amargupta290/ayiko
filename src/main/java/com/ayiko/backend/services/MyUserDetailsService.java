package com.ayiko.backend.services;

import com.ayiko.backend.model.User;
import com.ayiko.backend.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserUtil userUtil;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userUtil.findUserByUserName(userName);
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(userName, user.getPassword(),
                        new ArrayList<>());
            }
        return null;
    }
}