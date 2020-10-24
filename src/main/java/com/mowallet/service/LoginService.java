package com.mowallet.service;

import com.mowallet.domain.CustomUserDetails;
import com.mowallet.domain.User;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface LoginService {
    User getUserInfo(String user_name);

    CustomUserDetails getUserById(String user_name);
}
