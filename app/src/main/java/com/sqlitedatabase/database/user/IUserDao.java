package com.sqlitedatabase.database.user;

import com.sqlitedatabase.model.User;

import java.util.List;

/**
 * Created by asmaasamir1 on 11/9/17.
 */


public interface IUserDao {


    List<User> fetchAllUsers();

    // add user
    boolean addUser(User user);



}
