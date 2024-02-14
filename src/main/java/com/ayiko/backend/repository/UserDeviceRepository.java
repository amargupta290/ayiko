package com.ayiko.backend.repository;

import com.ayiko.backend.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer>{

	UserDevice findByUserIdAndDeviceId(Long userId, String deviceId);
	List<UserDevice> findByUserId(Long userId);
	
}
