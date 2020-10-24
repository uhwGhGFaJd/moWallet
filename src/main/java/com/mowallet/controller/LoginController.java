package com.mowallet.controller;

import com.mowallet.utils.AlertUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/21
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class LoginController {


    private final AlertUtil alertUtil;

    public LoginController(AlertUtil alertUtil) {
        this.alertUtil = alertUtil;
    }

    @GetMapping("/login")
    public String loginPage(String state, Principal principal, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (principal != null) {
            return "redirect:/";
        }

        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));

        if (state != null) {
            switch (state) {
                case "noauth":
                case "badlogin":
                    redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Bad Credentials".toUpperCase()));
                    break;
                case "expirationpassword":
                    redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Credentials Expired".toUpperCase()));
                    break;
                case "lock":
                    redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Locked".toUpperCase()));
                    break;
                case "erroruser":
                    redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Account Expired".toUpperCase()));
                    break;
                case "disabled":
                    redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Account Disabled".toUpperCase()));
                    break;
            }
        }
        return "pages/login/login";
    }

}
