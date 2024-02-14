package com.ayiko.backend.repository;

import com.ayiko.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByMobileNumber(String mobileNumber);

    public List<User> findByMobileNumberOrEmail(String mobileNumber, String email);
}
