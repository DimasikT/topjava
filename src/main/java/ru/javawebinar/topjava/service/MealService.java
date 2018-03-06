package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    Meal get(Integer id);

    Integer add(Meal meal);

    Integer delete(Integer id);

    List<Meal> getAll();
}
