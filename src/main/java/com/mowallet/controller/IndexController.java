package com.mowallet.controller;

import com.mowallet.domain.User;
import com.mowallet.mapper.JsonRpcDbMapper;
import com.mowallet.service.BitcoinJsonRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class IndexController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;
    private final JsonRpcDbMapper jsonRpcDbMapper;

    public IndexController(BitcoinJsonRpcService bitcoinJsonRpcService, JsonRpcDbMapper jsonRpcDbMapper) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
        this.jsonRpcDbMapper = jsonRpcDbMapper;
    }

    @GetMapping("/")
    public String indexPage(Principal principal, Model model, HttpSession session) {
        User user = (User) session.getAttribute("member");

        // Last 10 Transactions
        model.addAttribute("GetUserLast10Transactions", bitcoinJsonRpcService.getUserLast10Transactions(principal.getName()));
        // My Address list
        model.addAttribute("GetAddressesByLabel", jsonRpcDbMapper.getUserCreatedAddress(user.getUser_id()));
        // Address Balance
        BigDecimal userBalance = bitcoinJsonRpcService.getReceivedByLabel(principal.getName()).setScale(8, RoundingMode.DOWN);
        if (userBalance.signum() == 0) {
            userBalance = BigDecimal.ZERO;
        }
        model.addAttribute("getreceivedbylabel", userBalance);
        return "pages/index/index";
    }
}
