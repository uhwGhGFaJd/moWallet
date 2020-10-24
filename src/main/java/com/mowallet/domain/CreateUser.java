package com.mowallet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Getter
@Setter
public class CreateUser {
    @NotEmpty(message = "Username must not be empty")
    @NotBlank(message = "Username must not be blank")
    @Size(min = 1, max = 30, message = "Username size must be between {min} and {max}")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username can only be entered in English and numbers")
    private String user_name;

    @NotEmpty(message = "Password must not be empty")
    @NotBlank(message = "Password must not be blank")
    @Size(min = 1, max = 60, message = "Password size must be between {min} and {max}")
    private String password;

    @NotEmpty(message = "ConfirmPassword must not be empty")
    @NotBlank(message = "ConfirmPassword must not be blank")
    @Size(min = 1, max = 60, message = "ConfirmPassword size must be between {min} and {max}")
    private String confirmPassword;
}
