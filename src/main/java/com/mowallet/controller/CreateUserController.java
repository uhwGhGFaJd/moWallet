package com.mowallet.controller;

import com.mowallet.domain.CreateUser;
import com.mowallet.service.CreateUserService;
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
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/23
 * Github       : https://github.com/uhwGhGFaJd
 */
@Controller
public class CreateUserController {

    private final CreateUserService createUserService;
    private final AlertUtil alertUtil;

    public CreateUserController(CreateUserService createUserService, AlertUtil alertUtil) {
        this.createUserService = createUserService;
        this.alertUtil = alertUtil;
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/create/user")
    public String createUserPage() {

        return "pages/login/create_user";
    }

    @PostMapping("/create/user/post")
    public String createUserPost(@Valid CreateUser createUser, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
            return "redirect:/create/user";
        }else if(!createUser.getPassword().equals(createUser.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Password does not matched"));
            return "redirect:/create/user";
        }else if(createUserService.checkUsername(createUser.getUsername())){
            redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("danger", "Username already exists"));
            return "redirect:/create/user";
        }

        createUserService.insertNewUser(createUser);
        redirectAttributes.addFlashAttribute("msg", alertUtil.makeAlert("success", "Your account has been created"));
        return "redirect:/login";
    }
}
