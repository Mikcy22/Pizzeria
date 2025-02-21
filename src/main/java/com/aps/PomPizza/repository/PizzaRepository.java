package com.aps.PomPizza.repository;

import com.aps.PomPizza.models.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

}
