package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2018,  Month.MARCH, 1, 10, 0), "Завтрак", 500);

    public static final AtomicInteger GEN = new AtomicInteger(START_SEQ + 1);

    public static final LocalDateTime START_DATE = LocalDateTime.of(2018, Month.MARCH, 1, 9, 0);
    public static final LocalDateTime END_DATE = LocalDateTime.of(2018, Month.MARCH, 1, 11, 0);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
