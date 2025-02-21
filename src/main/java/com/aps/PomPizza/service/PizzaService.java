package com.aps.PomPizza.service;

import com.aps.PomPizza.models.Pizza;
import com.aps.PomPizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public String addPizza(Pizza pizza){
        pizzaRepository.save(pizza);
        return "Pizza creada correctamente";
    }

    public String deletePizza(String id){
        pizzaRepository.deleteById(id);
        return "Pizza borrada correctamente";
    }
}
