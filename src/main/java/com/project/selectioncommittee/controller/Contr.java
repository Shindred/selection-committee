package com.project.selectioncommittee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Contr {

    public String dos() {
        return "index";
    }
}
