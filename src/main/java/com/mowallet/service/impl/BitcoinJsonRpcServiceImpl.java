package com.mowallet.service.impl;

import com.mowallet.domain.GetUserListTransactions;
import com.mowallet.jsonrpc.BitcoinJsonRPC;
import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class BitcoinJsonRpcServiceImpl implements BitcoinJsonRpcService {

    private final BitcoinJsonRPC bitcoinJsonRPC;

    public BitcoinJsonRpcServiceImpl(BitcoinJsonRPC bitcoinJsonRPC) {
        this.bitcoinJsonRPC = bitcoinJsonRPC;
    }

    @Override
    public List<GetUserListTransactions> GetUserLast10Transactions(String user_name) {
        List<GetUserListTransactions> list = new ArrayList<>();



        return null;
    }
}
