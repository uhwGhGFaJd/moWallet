package com.mowallet.service;

import com.mowallet.domain.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface BitcoinJsonRpcService {

    void walletInit();

    void loadUserWallet(String user_name);

    void unLoadWallet(String user_name);

    List<GetUserLast10Transactions> getUserLast10Transactions(String user_name);

    List<GetAddressesByLabel> getAddressesByLabel(String user_name);

    UserBalance getBalances(String user_name);

    void getNewAddress(getNewAddressPost getNewAddressPost, HttpSession httpSession);

    void withdrawBitcoinAndInsertDb(WithdrawPost withdrawPost, Principal principal, HttpSession httpSession);
}
