package com.mowallet.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Component
public class CheckValidUtil {

    private final AlertUtil alertUtil;

    public CheckValidUtil(AlertUtil alertUtil) {
        this.alertUtil = alertUtil;
    }

    private String verificationCheck(BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
        return "redirect:/create/user";
    }

}
