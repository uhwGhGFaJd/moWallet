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
public class AddressInfoController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;

    public AddressInfoController(BitcoinJsonRpcService bitcoinJsonRpcService) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
    }

    @GetMapping("/addressInfo/{address}")
    public String AddressInfoPage(@PathVariable String address, Model model, HttpSession httpSession){

        model.addAttribute("addressInfo", bitcoinJsonRpcService.getAddressInfo(address, httpSession));
        return "pages/addressInfo/addressInfo";
    }
}
