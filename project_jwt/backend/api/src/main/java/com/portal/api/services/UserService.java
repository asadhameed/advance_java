package com.portal.api.services;

import com.portal.api.domain.User;
import com.portal.api.exception.domain.EmailExistException;
import com.portal.api.exception.domain.UserNameExistException;
import com.portal.api.exception.domain.UserNotFoundException;

import java.util.List;

public interface UserService {
    User register(String firstName, String lastName, String userName, String email) throws UserNotFoundException, EmailExistException, UserNameExistException;

    List<User> getUsers();

    User findUserByUserName(String userName);

    User findUserByEmail(String email);
}
