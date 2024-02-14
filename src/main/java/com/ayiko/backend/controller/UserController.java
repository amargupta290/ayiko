package com.ayiko.backend.controller;

import com.ayiko.backend.constants.Constants;
import com.ayiko.backend.dto.*;
import com.ayiko.backend.exception.BadCredentialException;
import com.ayiko.backend.exception.ResourceNotFoundException;
import com.ayiko.backend.model.*;
import com.ayiko.backend.repository.UserDeviceRepository;
import com.ayiko.backend.repository.UserRepository;
import com.ayiko.backend.services.MyUserDetailsService;
import com.ayiko.backend.services.UserService;
import com.ayiko.backend.util.JwtUtil;
import com.ayiko.backend.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {Constants.ORIGIN_LOCALHOST, Constants.ORIGIN_ALLOW_ALL})
public class UserController {

    private static List<String> countryList = new ArrayList<>();
    @Autowired
    UserUtil userUtil;
    @Autowired
    UserDeviceRepository userDeviceRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserDtoAssembler userDtoAssembler;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialException("Incorrect credentials provided or User is inactive.");
        }

        String jwt = null;
        try {
            jwt = fetchJwt(authenticationRequest.getUsername());
            User user = userUtil.findUserByUserName(authenticationRequest.getUsername());
            if (authenticationRequest.getDeviceId() != null && !authenticationRequest.getDeviceId().isBlank()) {
                if (userDeviceRepository.findByUserIdAndDeviceId(user.getId(), authenticationRequest.getDeviceId()) == null) {
                    saveUserDeviceEntry(jwt, authenticationRequest.getDeviceId(), user.getId());
                }
            }
        } catch (Exception exception) {
            throw new ResourceNotFoundException("User details not found or User is inactive.");
        }

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private void saveUserDeviceEntry(String jwt, String deviceId, Long userId) {
        UserDevice userDevice = new UserDevice();
        userDevice.setUserId(userId);
        userDevice.setDeviceId(deviceId);
        userDeviceRepository.save(userDevice);
    }

    private String fetchJwt(String userName) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(userName);
        String jwt = jwtTokenUtil.generateToken(userDetails);
        return jwt;
    }

    @PostMapping(value = "/signup")
    public Long createUser(@RequestBody @Valid SignUpDto dto) throws Exception {
        return userService.signUp(dto);
    }

    //@PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto, @RequestHeader("Authorization") String jwtToken) throws Exception {
        User user = userUtil.extractUser(jwtToken);
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(userDto.getCountry());
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setCountryCode(userDto.getCountryCode());
        user.setProfileImage(userDto.getProfileImage() != null ? userDto.getProfileImage().getBytes() : null);
        user = userRepository.save(user);
        return userDtoAssembler.doAssemble(user, true, false);
    }

    //@PutMapping("/admin")
    public UserDto updateUserViaAdmin(@RequestBody UserDto userDto, @RequestHeader("userName") String userName) throws Exception {
        User user = userUtil.findUserByUserName(userName);
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(userDto.getCountry());
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user = userRepository.save(user);
        return userDtoAssembler.doAssemble(user, true, false);
    }

    @PostMapping(value = "/reset-password")
    public void resetPassword(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        User user = userUtil.findUserByUserName(authenticationRequest.getUsername());

        user.setPassword(authenticationRequest.getPassword());
        userRepository.save(user);
    }


    //@GetMapping("/getDetails")
    public UserDto getUserDetails(@RequestHeader("Authorization") String jwtToken) {
        User user = userUtil.extractUser(jwtToken);
        return userDtoAssembler.doAssemble(user, false, false);
    }



    //@GetMapping("/admin/{userName}")
    public UserDto getUser(@PathVariable("userName") String userName) {
        User user = userUtil.findUserByUserName(userName);
        return userDtoAssembler.doAssemble(user, true, false);
    }

    //@PatchMapping("{id}/admin/active-inactive")
    public void markUserStatus(@PathVariable("id") Long id,
                               @RequestBody Boolean status) {

        User user = userRepository.findById(id).get();
        user.setActive(status);
        userRepository.save(user);
    }

    //@GetMapping("/admin/getAll")
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(obj -> userDtoAssembler.doAssemble(obj, true, true)).collect(Collectors.toList());
    }


    //@GetMapping("/countries")
    public List<String> getSignupCountries() throws FileNotFoundException, IOException {


        if (countryList.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/countries.txt")))) {
                for (String line; (line = br.readLine()) != null; ) {
                    countryList.add(line);
                }
            }
        }

        return countryList;
    }


    //@GetMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
