package com.aps.PomPizza.controller;

import com.aps.PomPizza.models.Pizza;
import com.aps.PomPizza.repository.PizzaRepository;
import com.aps.PomPizza.service.PizzaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService service;

    private PizzaRepository pizzaRepository;

    public PizzaRestController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @PostMapping("/addNewPizza")
    public String addNewPizza(@RequestBody Pizza pizza){
        return service.addPizza(pizza);
    }

    @PostMapping("/deletePizza")
    public String deletePizza(@RequestParam String id){
        return service.deletePizza(id);
    }

    @PostMapping("/editPizza")
    public String editPizza(@RequestBody Map<String, Object> body){

        //Lo hago con Map para poder mapear el id sin que se raye al pasarlo de String a ObjectId

        ObjectId id = new ObjectId(body.get("_id").toString());
        Pizza pizza = new Pizza();

        pizza.set_id(id);
        pizza.setNombre(body.get("nombre").toString());
        pizza.setDescripcion(body.get("descripcion").toString());
        pizza.setIngredientes((List<String>) body.get("ingredientes"));
        pizza.setPrecio(Double.parseDouble(body.get("precio").toString()));
        pizza.setImageUrl(body.get("imageUrl").toString());

        return service.editPizza(pizza);
    }

    @GetMapping()
    public List<Pizza> allPizzas(){
        return pizzaRepository.findAll();
    }
}
