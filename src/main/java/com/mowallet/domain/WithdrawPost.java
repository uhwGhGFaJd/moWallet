package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
public class WithdrawPost {
    private String withdraw_desc;
    private String withdraw_to;
    private BigDecimal withdraw_amount;
}
