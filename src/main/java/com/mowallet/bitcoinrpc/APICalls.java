package com.mowallet.bitcoinrpc;

import lombok.ToString;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/11/02
 * Github       : https://github.com/uhwGhGFaJd
 */
@ToString
public enum APICalls {
    WALLET_INIT("walletInit"),
    LOAD_USER_WALLET("loadUserWallet"),
    UNLOAD_WALLET("unLoadWallet"),
    LIST_TRANSACTIONS("listtransactions"),
    GET_BALANCES("getbalances"),
    GET_NEW_ADDRESS("getNewAddress"),
    GET_ADDRESS_INFO("getaddressinfo");

    private final String value;

    APICalls(String value) {
        this.value = value;
    }
}
