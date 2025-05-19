package com.example.controller;

import com.example.domain.User;
import com.example.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {

    @GetMapping("")
    public String index(UserForm form, Model model) {
        return "exam04";
    }

    @PostMapping("/register")
    public String register(@Validated UserForm form,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            return index(form, model);
        }

        User user = new User();
        user.setName(form.getName());
        user.setAge(form.getAge());
        user.setComment(form.getComment());
        model.addAttribute("user", user);

        return "exam04-result";
    }
}
