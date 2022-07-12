package com.dapiedade.todo_list_java.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;


    /* READ */


    @GetMapping("/")
    public List<TodoDTO> getAllTodos() {
            List<TodoDTO> list = new ArrayList<>();
            for (Todo t : todoService.getAll()) {
                list.add(todoService.todoToDto(t));
            }
            return list;
    }

    @GetMapping("/get/{id}")
    public TodoDTO getATodo(@PathVariable("id") long id) {
        try {
            System.out.println(todoService.todoToDto(todoService.get(id)));
            return todoService.todoToDto(todoService.get(id));
        } catch (Exception e) {
            return null;
        }
    }

    /* CREATE */

    @PostMapping("/save")
    public long saveTodo(@RequestBody TodoDTO todoDto) {
        todoDto.setState("Todo");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Todo>> constraintViolations = validator.validate(todoService.dtoToTodo(todoDto));
        if (constraintViolations.isEmpty()) {
            return todoService.save(todoService.dtoToTodo(todoDto));
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
    public boolean updateStateTodo(@PathVariable("id") long id, @RequestBody TodoDTO todoDto) {
        try {
            todoDto.setState("Completed");
            todoService.update(id, todoService.dtoToTodo(todoDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
