package com.ayiko.backend.repository;


import com.ayiko.backend.model.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOtpRepository extends JpaRepository<UserOtp, Integer> {
    UserOtp findByUserIdAndVerified(Long userId, Boolean verified);
}
