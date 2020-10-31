package com.mowallet.handler;

import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationLogoutHandler implements LogoutHandler {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;

    public AuthenticationLogoutHandler(BitcoinJsonRpcService bitcoinJsonRpcService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        bitcoinJsonRpcService.unLoadWallet(authentication.getName());
    }
}
