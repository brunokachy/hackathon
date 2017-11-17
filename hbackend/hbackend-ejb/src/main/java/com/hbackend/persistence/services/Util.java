package com.hbackend.persistence.services;

import com.hbackend.persistence.entity.portal.PortalUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class Util {

    @EJB
    PortalPersistence persistence;

    public static String hashPassword(String password) {
        String saltedPassword = ProjectConstant.SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    public Boolean checkHashedPassword(String enteredPassword, String storedPassword) {
        Boolean isAuthenticated;

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = ProjectConstant.SALT + enteredPassword;
        String hashedPassword = generateHash(saltedPassword);

        isAuthenticated = hashedPassword.equals(storedPassword);
        return isAuthenticated;
    }

    public PortalUser login(String emailAddress, String password) {
        PortalUser u = null;

        PortalUser ku = persistence.getActiveUserByEmailAddress(emailAddress);
        if (ku == null) {
            return u;
        }
        Boolean checkPassword = checkHashedPassword(password, ku.getPassword());
        if (checkPassword) {
            u = ku;
        }

        return u;
    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }
    

}
