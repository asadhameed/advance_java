package com.portal.api.resource;

import com.portal.api.domain.User;
import com.portal.api.exception.domain.EmailExistException;
import com.portal.api.exception.domain.ExceptionHandling;
import com.portal.api.exception.domain.UserNameExistException;
import com.portal.api.exception.domain.UserNotFoundException;
import com.portal.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/","/user"})
public class UserResource extends ExceptionHandling {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UserNameExistException {
       User newUser= this.userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());

       return  new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping
    public User showUser(){
        User u = new User();
        u.setUserName("Asad");
        return  u;
    }
}
