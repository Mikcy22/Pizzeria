package com.aps.PomPizza.controller;

import com.aps.PomPizza.models.Pizza;
import com.aps.PomPizza.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/pizzas")
@Controller
public class PizzaController {

    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping()
    public String showPizzas(){
        return "pizzas";
    }

    //Mostrar formulario para crear pizzas
    @GetMapping("/crearPizza")
    public String showAddPizza(){
        return "addPizza";
    }

    //Mostrar formulario para editar pizzas
    @GetMapping("/editarPizza/{id}")
    public String showEditPizza(@PathVariable String id, Model model){
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isEmpty()) {
            // Manejar el caso en el que no se encuentre la pizza
            return "redirect:/error"; // Puedes redirigir a una página de error o mostrar un mensaje
        }

        model.addAttribute("pizza", pizza.get());

        return "editPizza";
    }


}
