package com.mowallet.service;

import com.mowallet.domain.GetAddressesByLabel;
import com.mowallet.domain.GetUserLast10Transactions;
import com.mowallet.domain.WithdrawPost;
import com.mowallet.domain.getNewAddressPost;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface BitcoinJsonRpcService {

    List<GetUserLast10Transactions> getUserLast10Transactions(String user_name);

    List<GetAddressesByLabel> getAddressesByLabel(String user_name);

    BigDecimal getReceivedByLabel(String user_name);

    void getNewAddress(getNewAddressPost getNewAddressPost, HttpSession httpSession);

    void withdrawBitcoinAndInsertDb(WithdrawPost withdrawPost, Principal principal, HttpSession httpSession);
}
