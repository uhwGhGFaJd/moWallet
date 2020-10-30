package com.mowallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/29
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class TransactionsDetailController {

    @GetMapping("/transaction/{address}")
    public String transactionsDetailPage(@PathVariable String address){

        return null;
    }
}
