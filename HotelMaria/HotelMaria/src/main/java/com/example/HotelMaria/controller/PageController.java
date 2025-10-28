package com.example.HotelMaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/sobre")
    public String sobre(){
        return "sobre";
    }

    @GetMapping("/perfil")
    public String perfil(){
        return "perfil";
    }

    @GetMapping("/doar")
    public String doar(){
        return "doar";
    }
}
