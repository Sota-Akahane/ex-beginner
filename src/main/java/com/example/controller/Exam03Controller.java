package com.example.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {

    @Autowired
    private ServletContext application;

    @GetMapping("")
    public String index() {
        return "exam03";
    }

    @PostMapping("/purchase")
    public String purchase(String price1, String price2, String price3) {
        int intPrice1 = Integer.parseInt(price1);
        int intPrice2 = Integer.parseInt(price2);
        int intPrice3 = Integer.parseInt(price3);

        int taxExclusive = intPrice1 + intPrice2 + intPrice3;
        int taxInclusive = (int) (taxExclusive * 1.1);

        application.setAttribute("taxExclusive", taxExclusive);
        application.setAttribute("taxInclusive", taxInclusive);

        return "exam03-result";
    }
}
