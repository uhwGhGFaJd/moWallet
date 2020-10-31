package com.mowallet.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/30
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@Builder
public class TransactionsDetail {
    private String address;
    private String scriptPubKey;
    private boolean ismine;
    private boolean solvable;
    private String desc;
    private boolean iswatchonly;
    private boolean isscript;
    private boolean iswitness;
    private int witness_version;
    private String witness_program;
    private String pubkey;
    private boolean ischange;
    private String timestamp;
}
