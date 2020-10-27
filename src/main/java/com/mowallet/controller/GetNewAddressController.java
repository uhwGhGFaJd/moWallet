package com.mowallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/26
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class GetNewAddressController {

    @GetMapping("/getNewAddress")
    public String getNewAddressPage(){

        return null;
    }

    @PostMapping("/getNewAddress/post")
    public String getNewAddressPost(){

        return null;
    }
}
