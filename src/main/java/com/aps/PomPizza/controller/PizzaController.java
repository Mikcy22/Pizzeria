package com.aps.PomPizza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PizzaController {

    @GetMapping("/pizzas")
    public String showPizzas(){
        return "pizzas";
    }


}
