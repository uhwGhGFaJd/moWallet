package com.mowallet.controller;

import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.RoundingMode;
import java.security.Principal;

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

    @GetMapping("/")
    public String indexPage(Principal principal, Model model) {

        model.addAttribute("GetUserLast10Transactions", bitcoinJsonRpcService.getUserLast10Transactions(principal.getName()));
        model.addAttribute("GetAddressesByLabel", bitcoinJsonRpcService.getAddressesByLabel(principal.getName()));
        model.addAttribute("getreceivedbylabel", bitcoinJsonRpcService.getReceivedByLabel(principal.getName()).setScale(8, RoundingMode.DOWN));

        return "pages/index/index";
    }
}
