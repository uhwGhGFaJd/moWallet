package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/29
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
public class UserBalance {
    private BigDecimal trusted;
    private BigDecimal untrusted_pending;
    private BigDecimal immature;
}
