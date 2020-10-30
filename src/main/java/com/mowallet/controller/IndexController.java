package com.mowallet.controller;

import com.mowallet.domain.User;
import com.mowallet.domain.UserBalance;
import com.mowallet.service.BitcoinJsonRpcService;
import com.mowallet.service.JsonRpcDbService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class IndexController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;
    private final JsonRpcDbService jsonRpcDbService;


    public IndexController(BitcoinJsonRpcService bitcoinJsonRpcService, JsonRpcDbService jsonRpcDbService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
        this.jsonRpcDbService = jsonRpcDbService;
    }

    @GetMapping("/")
    public String indexPage(Principal principal, Model model, HttpSession session) {
        User user = (User) session.getAttribute("member");

        // Last 10 Transactions
        model.addAttribute("GetUserLast10Transactions", bitcoinJsonRpcService.getUserLast10Transactions(principal.getName()));
        // My Address list
        model.addAttribute("GetAddressesByLabel", jsonRpcDbService.getUserCreatedAddress(user.getUser_id()));
        // Address Balance
        UserBalance userBalance = bitcoinJsonRpcService.getBalances(principal.getName());

        model.addAttribute("userBalance", userBalance);
        model.addAttribute("service_fees", jsonRpcDbService.getServiceFees());
        return "pages/index/index";
    }
}
