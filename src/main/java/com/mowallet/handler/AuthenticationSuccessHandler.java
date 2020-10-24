package com.mowallet.handler;


import com.mowallet.service.LoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final LoginService loginService;

    public AuthenticationSuccessHandler(LoginService loginService) {
        this.loginService = loginService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = httpServletRequest.getSession();

        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            // save userInfo
            session.setAttribute("member", loginService.getUserInfo(authentication.getName()));

            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(httpServletRequest, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(httpServletRequest, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(httpServletRequest, response, authentication);
        }


    }
}
