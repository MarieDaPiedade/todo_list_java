package com.dapiedade.todo_list_java.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dapiedade.todo_list_java.model.Todo;
import com.dapiedade.todo_list_java.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {

    private TodoService todoService;

    /* READ */

    @GetMapping("/")
    public List<Todo> getAllTodos() {
        try {
            return todoService.getAll();
        } catch (Exception e) {
            return Collections.emptyList(); // à revoir ?
        }
    }

    @GetMapping("/get/{id}")
    public Todo getATodo(@PathVariable("id") long id) {
        try {
            return todoService.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    /* CREATE */

    @PostMapping("/save")
    public long saveTodo(Todo todo) {
        todo.setState("Todo");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Todo>> constraintViolations = validator.validate(todo);
        if (constraintViolations.isEmpty()) {
            return todoService.save(todo);
        } else {
            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation<?> c : constraintViolations) {
                errors.put(c.getPropertyPath().toString(), c.getMessage());
            }
            return -1;
        }
    }

    /* UPDATE */

    @PutMapping("/update/{id}")
    public boolean updateStateTodo(@PathVariable("id") long id, @RequestBody Todo todo) {
        try {
            todoService.update(id, todo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
