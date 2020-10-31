package com.mowallet.controller;

import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/29
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class TransactionsDetailController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;

    public TransactionsDetailController(BitcoinJsonRpcService bitcoinJsonRpcService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
    }

    @GetMapping("/transaction/{address}")
    public String transactionsDetailPage(@PathVariable String address, Model model, HttpSession httpSession){

        model.addAttribute("transactionsDetail", bitcoinJsonRpcService.getTransactionsDetail(address, httpSession));
        return null;
    }
}
