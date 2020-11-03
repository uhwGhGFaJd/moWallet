package com.mowallet.bitcoinrpc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/11/02
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@Builder
@ToString
public class BitcoinRpcConfig {
    private String url;
    private String rpcPort;
    private String rpcUser;
    private String rpcPassword;
}
