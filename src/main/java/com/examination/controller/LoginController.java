package com.examination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/")
    String index() {
        return "redirect:/login";
    }
    @RequestMapping(value = "/login")
    String login() {
        return "login";
    }
}
