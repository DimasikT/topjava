package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealServiceImpl implements MealService{

    Map<Integer, Meal> repository = MealsUtil.meals;

    @Override
    public Meal get(Integer id) {
        return repository.get(id);
    }

    @Override
    public Integer add(Meal meal) {
        Integer id;
        if(meal.getId() == null) {
            id = MealsUtil.gen.incrementAndGet();
            repository.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        } else {
            id = meal.getId();
            repository.put(id, meal);
        }
        return id;
    }

    @Override
    public Integer delete(Integer id) {
        return repository.remove(id).getId();
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(repository.values());
    }
}
