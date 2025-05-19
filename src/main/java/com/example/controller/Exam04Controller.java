package com.example.controller;

import com.example.domain.User;
import com.example.form.UserForm;
import org.springframework.beans.BeanUtils;
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
    public String index(UserForm form) {
        return "exam04";
        // このメソッド内でリクエストスコープに格納する処理などを行っていないので、
        // 引数に Model model の記述は不要。
    }

    @PostMapping("/register")
    public String register(@Validated UserForm form,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            return index(form);
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
//        user.setName(form.getName());
//        user.setAge(form.getAge());
//        user.setComment(form.getComment());
        model.addAttribute("user", user);

        return "exam04-result";
    }
}
