package com.dapiedade.todo_list_java.service;

import java.util.List;

import com.dapiedade.todo_list_java.model.Todo;

public interface TodoService {
    
    public Todo get(long id);

    public List<Todo> getAll();

    public List<Todo> getAllByState(String state);

    public long save(Todo todo);

    public void update(long id, Todo todo);

    public void delete(long id);
}
