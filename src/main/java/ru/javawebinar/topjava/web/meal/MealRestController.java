package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;


import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal create(int userId, Meal meal){
        return service.create(userId, meal);
    }

    public void delete(int userId, int id){
        service.delete(userId, id);
    }

    public Meal get(int userId, int id) {
        return service.get(userId, id);
    }

    public void update(int userId, Meal meal){
        service.update(userId, meal);
    }

    public List<Meal> getAll(int userId){
        return service.getAll(userId);
    }

}