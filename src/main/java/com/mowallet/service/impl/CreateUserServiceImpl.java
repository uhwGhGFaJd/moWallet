package com.mowallet.service.impl;

import com.mowallet.domain.CreateUser;
import com.mowallet.mapper.CreateUserMapper;
import com.mowallet.service.CreateUserService;
import org.springframework.stereotype.Service;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final CreateUserMapper createUserMapper;

    public CreateUserServiceImpl(CreateUserMapper createUserMapper) {
        this.createUserMapper = createUserMapper;
    }

    @Override
    public boolean checkUsername(String user_name) {
        return createUserMapper.checkUsername(user_name);
    }

    @Override
    public void insertNewUser(CreateUser createUser) {
        createUserMapper.insertNewUser(createUser);
    }
}
