package com.mowallet.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/25
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@Builder
public class GetUserLast10Transactions {
    private String address;
    private String category;
    private BigDecimal amount;
    private int vout;
    private BigDecimal fee;
    private int confirmations;
    private String blockhash;
    private int blockheight;
    private int blockindex;
    private String blocktime;
    private String txid;
    private String time;
    private String timereceived;
}
