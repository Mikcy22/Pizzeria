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

    public String editPizza(Pizza pizza){

        if (pizza.get_id() == null) {
            return "Error: ID no válido.";
        }

        // Verificar si la pizza ya existe
        if (!pizzaRepository.existsById(pizza.get_id())) {
            return "Error: No se encontró la pizza con ese ID.";
        }

        pizzaRepository.save(pizza);
        return "Pizza editada correctamente";
    }
}
