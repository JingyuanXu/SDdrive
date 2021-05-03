package com.udacity.jwdnd.course1.cloudstorage.entity;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

public interface UserAbstract {
    public User getUserByName(String username);
    public int createUser(User user);
    public boolean isUserExist(String usename);
}
