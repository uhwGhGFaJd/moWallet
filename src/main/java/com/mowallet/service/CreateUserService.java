package com.mowallet.service;

import com.mowallet.domain.CreateUser;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface CreateUserService {
    boolean checkUsername(String user_name);

    void insertNewUser(CreateUser createUser);
}
