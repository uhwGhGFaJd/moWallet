package com.mowallet.handler;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        if (authException instanceof UsernameNotFoundException) {
            response.sendRedirect(request.getContextPath() + "/login?state=noauth");
        } else if (authException instanceof BadCredentialsException) {
            response.sendRedirect(request.getContextPath() + "/login?state=badlogin");
        } else if (authException instanceof CredentialsExpiredException) {
            response.sendRedirect(request.getContextPath() + "/login?state=expirationpassword");
        } else if (authException instanceof LockedException) {
            response.sendRedirect(request.getContextPath() + "/login?state=lock");
        } else if (authException instanceof AccountExpiredException) {
            response.sendRedirect(request.getContextPath() + "/login?state=erroruser");
        } else if (authException instanceof DisabledException) {
            response.sendRedirect(request.getContextPath() + "/login?state=disabled");
        }
    }
}
