package com.mowallet.bitcoinrpc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/11/02
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@Builder
public class BitcoinRpcConfig {
    private String rpcHost;
    private String rpcPort;
    private String rpcUsername;
    private String rpcPassword;
    private String rpcWallet;

    public String getBaseUrl() {
        return "http://" + rpcUsername + ":" + rpcPassword + "@" + rpcHost + ":" + rpcPort + "/wallet/" + rpcWallet;
    }

    public String getBasicAuth() {
        return "Basic " + new String(new Base64().encode((rpcUsername + ":" + rpcPassword).getBytes()));
    }

}
