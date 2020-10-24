package com.mowallet.service.impl;

import com.mowallet.domain.CreateUser;
import com.mowallet.mapper.CreateUserMapper;
import com.mowallet.service.CreateUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CreateUserMapper createUserMapper;

    public CreateUserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, CreateUserMapper createUserMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.createUserMapper = createUserMapper;
    }

    @Override
    public boolean checkUsername(String user_name) {
        return createUserMapper.checkUsername(user_name);
    }

    @Override
    public void insertNewUser(CreateUser createUser) {
        createUser.setPassword(bCryptPasswordEncoder.encode(createUser.getPassword()));
        createUserMapper.insertNewUser(createUser);
    }
}
