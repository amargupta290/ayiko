package com.ayiko.backend.services;

import com.ayiko.backend.dto.SignUpDto;
import com.ayiko.backend.dto.VerifyOtpDto;
import com.ayiko.backend.exception.CommonApplicationException;
import com.ayiko.backend.model.User;
import com.ayiko.backend.model.UserOtp;
import com.ayiko.backend.repository.UserOtpRepository;
import com.ayiko.backend.repository.UserRepository;
import com.ayiko.backend.util.OtpSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpSender otpSender;
    @Autowired
    private UserOtpRepository userOtpRepository;

    public void verifyOtp(VerifyOtpDto dto){
        UserOtp userOtp = userOtpRepository.findByUserIdAndVerified(dto.getUserId(), false);
        if(userOtp != null && userOtp.getOtp().equalsIgnoreCase(dto.getOtp())){
            userOtp.setVerified(true);
            userOtpRepository.save(userOtp);
        } else {
            throw new CommonApplicationException("Wrong OTP entered, please try again!");
        }
    }

    public void verifyUser(VerifyOtpDto dto){
        UserOtp userOtp = userOtpRepository.findByUserIdAndVerified(dto.getUserId(), false);
        if(userOtp != null && userOtp.getOtp().equalsIgnoreCase(dto.getOtp())){
            Optional<User> optionalUser = userRepository.findById(dto.getUserId());
            User user = optionalUser.get();
            user.setVerified(true);
            user.setActive(true);
            userRepository.save(user);
            userOtp.setVerified(true);
            userOtpRepository.save(userOtp);
        } else {
            throw new CommonApplicationException("Wrong OTP entered, please try again!");
        }
    }

    public Long signUp(SignUpDto dto){
        User existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser != null) {
            UserOtp userOtp = userOtpRepository.findByUserIdAndVerified(existingUser.getId(), false);
            if(userOtp != null){
                return existingUser.getId();
            } else {
                throw new CommonApplicationException("Email address already registered");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setSignupDate(new Date());
        user.setVerified(false);
        user = userRepository.save(user);
        return user.getId();
    }

}
