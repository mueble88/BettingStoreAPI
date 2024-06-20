package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolaMundoController {

    @RequestMapping("/saludar")
    public String saludar(){
        return "Hola mundo";
    }
}
