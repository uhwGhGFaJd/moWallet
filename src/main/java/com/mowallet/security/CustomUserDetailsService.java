package com.mowallet.security;


import com.mowallet.domain.CustomUserDetails;
import com.mowallet.service.LoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginService loginService;

    public CustomUserDetailsService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = loginService.getUserById(user_name);

        if (customUserDetails == null) {
            throw new UsernameNotFoundException(user_name);
        }

        return customUserDetails;
    }
}
