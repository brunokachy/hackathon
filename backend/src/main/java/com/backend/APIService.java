/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend;

import com.backend.entity.User_;
import com.backend.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIService {

    @Autowired
    Util util;
    UserRepository ur;

    @RequestMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/validateEmail")
    User_ validateEmail(@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
            @RequestParam("surname") String surname, @RequestParam("emailAddress") String emailAddress,
            @RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        User_ u = new User_();
        u.setActive(true);
        u.setEmailAddress(emailAddress);
        u.setFirstName(firstName);
        u.setModifiedDate(new Date());
        u.setSurname(surname);
        u.setPassword(util.hashPassword(password));
        ur.save(u);
        return u;
    }

    @RequestMapping("/createUser")
    User_ createUser(@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
            @RequestParam("surname") String surname, @RequestParam("emailAddress") String emailAddress,
            @RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password) {
        User_ u = new User_();
        u.setActive(true);
        u.setEmailAddress(emailAddress);
        u.setFirstName(firstName);
        u.setModifiedDate(new Date());
        u.setSurname(surname);
        u.setPassword(util.hashPassword(password));
        ur.save(u);
        return u;
    }

}
