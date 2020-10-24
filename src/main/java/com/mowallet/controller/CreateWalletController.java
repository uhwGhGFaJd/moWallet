package com.mowallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class CreateWalletController {

    @GetMapping("/create/user")
    public String createWalletPage(){

        return "/pages/login/create_wallet";
    }
}
