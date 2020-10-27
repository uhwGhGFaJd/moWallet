package com.mowallet.controller;

import com.mowallet.domain.getNewAddressPost;
import com.mowallet.service.BitcoinJsonRpcService;
import com.mowallet.utils.AlertUtil;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/26
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class GetNewAddressController {

    private final BitcoinJsonRpcService bitcoinJsonRpcService;
    private final AlertUtil alertUtil;

    public GetNewAddressController(BitcoinJsonRpcService bitcoinJsonRpcService, AlertUtil alertUtil) {
        this.bitcoinJsonRpcService = bitcoinJsonRpcService;
        this.alertUtil = alertUtil;
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/getNewAddress")
    public String getNewAddressPage() {

        return "pages/newAddress/newAddress";
    }

    @PostMapping("/getNewAddress/post")
    public String getNewAddressPost(@Valid getNewAddressPost getNewAddressPost, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
            return "redirect:/getNewAddress";
        }

        bitcoinJsonRpcService.getNewAddress(getNewAddressPost, session);
        redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("success", "The address has been created"));
        return "redirect:/getNewAddress";
    }
}
