package com.intecap.ctiniguar.JWT.controller.Demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "/home")
    public String welcome(){
        return "Bienvenido a la pagina protegida de demo";
    }
}
