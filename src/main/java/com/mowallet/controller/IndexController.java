package com.mowallet.controller;

import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class IndexController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;

    public IndexController(BitcoinJsonRpcService bitcoinJsonRpcService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
    }

    @GetMapping("/index")
    public String indexPage(Model model) {

        model.addAttribute("GetUserLast10Transactions", bitcoinJsonRpcService.GetUserLast10Transactions("asd"));

        return "pages/index/index";
    }
}
