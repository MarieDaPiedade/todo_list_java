package com.dapiedade.todo_list_java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dapiedade.todo_list_java.model.Todo;
import com.dapiedade.todo_list_java.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo get(long id) {
        Optional<Todo> todoOption = todoRepository.findById(id);
        if (todoOption.isEmpty()) {
            throw new NullPointerException();
        } else {
            return todoOption.get();
        }
    }

    @Override
    public List<Todo> getAll() {
        return (List<Todo>) todoRepository.findAll();
    }

    @Override
    public List<Todo> getAllByState(String state) { // à revoir exception
        return todoRepository.findAllByState(state);
    }

    @Override
    public long save(Todo todo) {
        return todoRepository.save(todo).getId();
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }
}
