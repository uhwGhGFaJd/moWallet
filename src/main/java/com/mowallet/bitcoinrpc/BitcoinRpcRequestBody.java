package com.mowallet.bitcoinrpc;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/11/02
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
public class BitcoinRpcRequestBody {
    private String method;
    private String id;
    private Object[] params = new Object[]{};
}
