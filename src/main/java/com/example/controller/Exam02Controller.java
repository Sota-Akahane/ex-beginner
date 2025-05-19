package com.example.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam02")
public class Exam02Controller {

    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String index() {
        return "exam02";
    }

    @PostMapping("/input")
    public String input(String num1, String num2) {
        int intNum1 = Integer.parseInt(num1);
        int intNum2 = Integer.parseInt(num2);
        int answer = intNum1 + intNum2;

        session.setAttribute("num1", num1);
        session.setAttribute("num2", num2);
        session.setAttribute("answer", answer);

        return "exam02-result";
    }

    @GetMapping("/result")
    public String result() {
        return "exam02-result2";
    }
}
