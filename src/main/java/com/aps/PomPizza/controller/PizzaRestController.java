package com.aps.PomPizza.controller;

import com.aps.PomPizza.models.Pizza;
import com.aps.PomPizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService service;

    @PostMapping("/addNewPizza")
    public String addNewPizza(@RequestBody Pizza pizza){
        return service.addPizza(pizza);
    }

    @PostMapping("/deletePizza")
    public String deletePizza(@RequestParam String id){
        return service.deletePizza(id);
    }


}
