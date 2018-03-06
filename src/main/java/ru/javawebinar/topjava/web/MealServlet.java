package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {

    MealService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new MealServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id_param = request.getParameter("id");
        Integer id = id_param.isEmpty() ? null : Integer.parseInt(id_param);
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        service.add(new Meal(id, date, description, calories));
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            Meal update = service.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("id", update.getId());
            request.setAttribute("date", update.getDateTime());
            request.setAttribute("description", update.getDescription());
            request.setAttribute("calories", update.getCalories());
            request.setAttribute("action", "update");
            request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(service.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            service.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(service.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            response.sendRedirect("meals");
        } else {
            request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(service.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }
}
