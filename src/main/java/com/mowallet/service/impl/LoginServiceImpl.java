package com.mowallet.service.impl;

import com.mowallet.domain.CustomUserDetails;
import com.mowallet.domain.User;
import com.mowallet.mapper.LoginMapper;
import com.mowallet.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public User getUserInfo(String user_name) {
        return loginMapper.getUserInfo(user_name);
    }

    @Override
    public CustomUserDetails getUserById(String user_name) {
        return loginMapper.getUserById(user_name);
    }
}
