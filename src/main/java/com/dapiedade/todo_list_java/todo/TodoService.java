package com.dapiedade.todo_list_java.todo;

import java.util.List;

public interface TodoService {

    public Todo get(long id);

    public List<Todo> getAll();

    public List<Todo> getAllByState(String state);

    public long save(Todo todo);

    public Todo updateState(long id, Todo todo);

    public TodoDTO todoToDto(Todo todo);

    public Todo dtoToTodo(TodoDTO tdto);
}
