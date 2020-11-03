package com.mowallet.service.impl;

import com.mowallet.domain.CreateUser;
import com.mowallet.bitcoinrpc.BitcoinJsonRPC;
import com.mowallet.mapper.CreateUserMapper;
import com.mowallet.service.CreateUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CreateUserMapper createUserMapper;
    private final BitcoinJsonRPC bitcoinJsonRPC;

    public CreateUserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, CreateUserMapper createUserMapper, BitcoinJsonRPC bitcoinJsonRPC) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.createUserMapper = createUserMapper;
        this.bitcoinJsonRPC = bitcoinJsonRPC;
    }

    @Override
    public boolean checkUsername(String user_name) {
        return createUserMapper.checkUsername(user_name);
    }

    @Override
    @Transactional
    public void insertNewUser(CreateUser createUser) {
        bitcoinJsonRPC.requestJsonRpc("createwallet", createUser.getUsername(), true, createUser.getUsername());
        createUser.setPassword(bCryptPasswordEncoder.encode(createUser.getPassword()));
        createUserMapper.insertNewUser(createUser);
    }
}
