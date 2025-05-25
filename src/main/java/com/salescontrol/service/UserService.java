package com.salescontrol.service;

import com.salescontrol.data.user.UserDao;
import com.salescontrol.domain.User;
import java.util.Optional;

public class UserService {

    private final UserDao userDao = new UserDao();

    public Optional<User> authenticate(String login, String password) {
        return userDao.authenticate(login, password);
    }
}
