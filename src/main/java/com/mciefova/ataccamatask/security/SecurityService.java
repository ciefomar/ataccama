package com.mciefova.ataccamatask.security;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String encryptPassword (String password) {
        //use some encryption strategy
        return new StringBuilder().append("+++").append(password).toString();
    }

    public String decryptPassword (String password) {
        return password.substring(3, password.length());
    }

    public String maskPassword () {
        //something clever
        return "****";
    }


}
