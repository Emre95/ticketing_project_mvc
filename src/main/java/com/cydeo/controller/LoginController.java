package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping
    public String method1() {

        return "/login";
    }

    @GetMapping("/login")
    public String method2() {

        return "/login";
    }


}
