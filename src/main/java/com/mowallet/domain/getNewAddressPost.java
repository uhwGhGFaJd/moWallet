package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
public class getNewAddressPost {
    private int user_id;
    private String user_name;

    @NotNull(message = "Address Description must not be null")
    @NotBlank(message = "Address Description must not be blank")
    @Size(min = 1, max = 5000, message = "Address Description size must be between {min} and {max}")
    private String desc;

    private String address;
}
