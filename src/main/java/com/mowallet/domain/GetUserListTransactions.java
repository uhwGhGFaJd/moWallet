package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/25
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@ToString
public class GetUserListTransactions {
    private String address;
    private String category;
    private String amount;
    private String vout;
    private int confirmations;
    private String blockhash;
    private int blockheight;
    private int blockindex;
    private String blocktime;
    private String txid;
    private String timereceived;
}
