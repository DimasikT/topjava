package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(1, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> userMeals = repository.get(userId);

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (userMeals == null) {
                repository.put(userId, new HashMap<>());
            }
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        } else {
            if (userMeals == null) {
                return null;
            }
            return userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
    }

    @Override
    public boolean delete(int userId, int id) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        if (userMeals == null) {
            return null;
        } else {
            return userMeals.get(id);
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals == null ? Collections.EMPTY_LIST : userMeals.values();
    }
}

