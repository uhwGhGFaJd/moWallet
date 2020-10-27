package com.mowallet.service;

import com.mowallet.domain.GetUserLast10Transactions;

import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
public interface BitcoinJsonRpcService {

    List<GetUserLast10Transactions> GetUserLast10Transactions(String user_name);
}
