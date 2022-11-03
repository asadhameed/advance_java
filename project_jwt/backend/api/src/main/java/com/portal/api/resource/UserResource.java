package com.portal.api.resource;

import com.portal.api.constant.SecurityConstant;
import com.portal.api.domain.User;
import com.portal.api.domain.UserPrinciple;
import com.portal.api.exception.domain.EmailExistException;
import com.portal.api.exception.domain.ExceptionHandling;
import com.portal.api.exception.domain.UserNameExistException;
import com.portal.api.exception.domain.UserNotFoundException;
import com.portal.api.services.UserService;
import com.portal.api.utility.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/","/user"})
public class UserResource extends ExceptionHandling {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> userAuthenticate(@RequestBody User user) throws UserNotFoundException, EmailExistException, UserNameExistException {

        User newUser= this.userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());

        return  new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> register(@RequestBody User user) {
        authenticate(user.getUserName(), user.getPassword());

        User loginUser = this.userService.findUserByUserName(user.getUserName());
        UserPrinciple userPrinciple = new UserPrinciple(loginUser);

        HttpHeaders jwtHttpHeaders = getJwtHeader(userPrinciple);
        return  new ResponseEntity<>(loginUser,jwtHttpHeaders, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(UserPrinciple userPrinciple) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER, this.jwtTokenProvider.generateJwtToken(userPrinciple));
        return headers;
    }

    private void authenticate(String userName, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
    }
}
