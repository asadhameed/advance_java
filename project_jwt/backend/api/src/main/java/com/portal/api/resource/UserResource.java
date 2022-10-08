package com.portal.api.resource;

import com.portal.api.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {
    @GetMapping
    public User showUser(){
        User u = new User();
        u.setUserName("Asad");
        return  u;
    }
}
