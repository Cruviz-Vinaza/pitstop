package com.formula1.pitstop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloPitstop {

    @GetMapping("/saludar")
    public String sayHello(){
        return "¡Bienvenido a PitStop!";
    }
}
