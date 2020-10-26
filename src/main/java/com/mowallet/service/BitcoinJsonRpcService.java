package com.mowallet.service;

import com.mowallet.domain.GetUserListTransactions;

import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface BitcoinJsonRpcService {
    List<GetUserListTransactions> GetUserLast10Transactions(String user_name);
}
