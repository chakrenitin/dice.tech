package com.dice.weather.controller;

import com.dice.weather.dao.UserDao;
import com.dice.weather.dto.AuthenticationRequest;
import com.dice.weather.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request){

        final UserDetails user = userDao.findUserNameByClientId(request.getClientId());

        if(user!=null){

            if(!StringUtils.equals(user.getPassword(),request.getClientSecret())){
                return ResponseEntity.status(401).body("Unauthorized");
            }

            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some Error Occurred");
    }
}
