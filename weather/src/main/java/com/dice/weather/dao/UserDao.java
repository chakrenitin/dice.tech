package com.dice.weather.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "dicetechuser",
                    "f2a1ed52710d4533bde25be6da03b6e3",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserNameByClientId(String clientID){
       return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(clientID))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("No User Found"));
    }
}
