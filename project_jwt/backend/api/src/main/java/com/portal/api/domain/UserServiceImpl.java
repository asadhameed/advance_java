package com.portal.api.domain;

import com.portal.api.enumeration.Role;
import com.portal.api.exception.domain.EmailExistException;
import com.portal.api.exception.domain.UserNameExistException;
import com.portal.api.exception.domain.UserNotFoundException;
import com.portal.api.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.portal.api.constant.UserImplConstant.*;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {


    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
      //  this.passwordEncoder = passwordEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUserName(username);
        if(user == null) {
            logger.error("User not found by UserName: [{}]", username);
            throw new UsernameNotFoundException("User not found by UserName: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            this.userRepository.save(user);
            UserPrinciple userPrinciple = new UserPrinciple(user);
            logger.info("Returning the found user: [{}]", username);
            return  userPrinciple;
        }
    }

    @Override
    public User register(String firstName, String lastName, String userName, String email) throws UserNotFoundException, EmailExistException, UserNameExistException {
        User newUser = this.validateNewUserNameAndEmail(StringUtils.EMPTY, userName, email);
        if(newUser == null){
            newUser = new User();
            newUser.setUserId(this.generateUserId());
            String password = this.generatedPassword();
            String enCodePassword = this.enCodePassword(password);
            newUser.setPassword(enCodePassword);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setUserName(userName);
            newUser.setEmail(email);
            newUser.setJoinDate(new Date());
            newUser.setActive(true);
            newUser.setNotLocked(true);
            newUser.setRoles(Role.ROLE_USER.name());
            newUser.setAuthorities(Role.ROLE_USER.getAuthorities());
            newUser.setProfileUrl(this.getTemporaryProfileImageUrl());
            this.userRepository.save(newUser);
            logger.info("New User password " + password);

        }
        return newUser;
    }

    private String getTemporaryProfileImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PROFILE_TEMP).toUriString();
    }

    private String enCodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    private String generatedPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUserNameAndEmail(String currentUserName,String newUserName, String newEmail) throws UserNotFoundException, UserNameExistException, EmailExistException {
        User userByNewUserName = this.findUserByUserName(newUserName);
        User userByNewEmail = this.findUserByEmail(newEmail);

        if(StringUtils.isNotBlank(currentUserName)){
            User currentUser = this.findUserByUserName(currentUserName);

            if(currentUser == null){
                throw new UserNotFoundException(NO_USER_FOUND_BY_USER_NAME  + currentUserName);
            }



            if(userByNewUserName != null && !currentUser.getId().equals(userByNewUserName.getUserId())){
                throw new UserNameExistException(USER_ALREADY_EXISTS);
            }



            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())){
                throw new EmailExistException(USER_ALREADY_EXISTS);
            }
            return currentUser;
        } else{


            if(userByNewUserName != null ){
                throw new UserNameExistException(USER_ALREADY_EXISTS);
            }



            if(userByNewEmail != null){
                throw new EmailExistException(USER_ALREADY_EXISTS);
            }

            return  null;
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.userRepository.findUserByUserName(userName);
    }

    @Override
    public User findUserByEmail(String email) {
        return  this.userRepository.findUserByEmail(email);
    }
}
