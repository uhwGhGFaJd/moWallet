package com.mowallet.controller;

import com.mowallet.domain.WithdrawPost;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class WithdrawController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @GetMapping("/withdraw")
    public String withdrawPage() {

        return "pages/withdraw/withdraw";
    }

    @PostMapping("/withdraw/post")
    public String withdrawPost(@Valid WithdrawPost withdrawPost, BindingResult bindingResult) {

        return null;
    }
}
