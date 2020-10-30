package com.mowallet.listener;

import com.mowallet.domain.User;
import com.mowallet.service.BitcoinJsonRpcService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/30
 * Github       : https://github.com/uhwGhGFaJd
 */
@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;

    public CustomHttpSessionListener(BitcoinJsonRpcService bitcoinJsonRpcService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = (User) session.getAttribute("member");
        if (user != null) {
            bitcoinJsonRpcService.unLoadWallet(user.getUser_name());
        }
    }
}
