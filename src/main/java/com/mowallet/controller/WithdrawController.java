package com.mowallet.controller;

import com.mowallet.domain.WithdrawPost;
import com.mowallet.service.JsonRpcDbService;
import com.mowallet.utils.AlertUtil;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final JsonRpcDbService jsonRpcDbService;

    public WithdrawController(AlertUtil alertUtil, JsonRpcDbService jsonRpcDbService) {
        this.alertUtil = alertUtil;
        this.jsonRpcDbService = jsonRpcDbService;
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @GetMapping("/withdraw")
    public String withdrawPage(Model model) {

        model.addAttribute("service_fees", jsonRpcDbService.getServiceFees());
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
