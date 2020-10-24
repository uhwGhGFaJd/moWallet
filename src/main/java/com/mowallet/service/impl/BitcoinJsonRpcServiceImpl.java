package com.mowallet.service.impl;

import com.mowallet.jsonrpc.JsonRPC;
import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Service;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Service
public class BitcoinJsonRpcServiceImpl implements BitcoinJsonRpcService {

    private final JsonRPC jsonRPC;

    public BitcoinJsonRpcServiceImpl(JsonRPC jsonRPC) {
        this.jsonRPC = jsonRPC;
    }
}
