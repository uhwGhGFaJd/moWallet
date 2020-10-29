package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
@ToString
public class WithdrawPost {
    @NotNull(message = "bitcoin address must not be null")
    @NotBlank(message = "bitcoin address must not be blank")
    @Size(min = 1, max = 5000, message = "Withdraw Description size must be between {min} and {max}")
    private String withdraw_desc;

    @NotNull(message = "bitcoin address must not be null")
    @NotBlank(message = "bitcoin address must not be blank")
    @Pattern(regexp = "^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$", message = "Not a valid bitcoin address.")
    private String withdraw_to;

    @NotNull(message = "Withdraw Amount address must not be null")
    @NotBlank(message = "Withdraw Amount address must not be blank")
    @DecimalMin(value = "0.00001", message = "Withdraw Amount must be greater than ${inclusive == true ? 'or equal to ' : ''}{value}")
    private BigDecimal withdraw_amount;

    private int user_id;
    private int service_fees;
}
