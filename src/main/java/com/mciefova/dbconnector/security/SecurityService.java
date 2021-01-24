package com.mciefova.dbconnector.security;

import org.springframework.stereotype.Component;

/**
 * Service providing password security operations.
 */
@Component
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
