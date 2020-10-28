package com.mowallet.controller;

import com.mowallet.domain.WithdrawPost;
import com.mowallet.utils.AlertUtil;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/27
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class WithdrawController {

    private final AlertUtil alertUtil;

    public WithdrawController(AlertUtil alertUtil) {
        this.alertUtil = alertUtil;
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @GetMapping("/withdraw")
    public String withdrawPage() {

        return "pages/withdraw/withdraw";
    }

    @PostMapping("/withdraw/post")
    public String withdrawPost(@Valid WithdrawPost withdrawPost, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
            return "redirect:/withdraw";
        }

        System.out.println(withdrawPost.toString());

        return null;
    }
}
