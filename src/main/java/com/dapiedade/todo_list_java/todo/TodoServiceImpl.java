package com.dapiedade.todo_list_java.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void update(long id, Todo todo) {
        todo.setId(id);
        update(id, todo);
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDTO todoToDto(Todo todo) {
        TodoDTO tdto = new TodoDTO();
        tdto.setId(todo.getId());
        tdto.setTitle(todo.getTitle());
        tdto.setState(todo.getState());
        tdto.setDescription(todo.getDescription());

        return tdto;
    }

    @Override
    public Todo dtoToTodo(TodoDTO tdto) {
        Todo t = new Todo();
        t.setId(tdto.getId());
        t.setTitle(tdto.getTitle());
        t.setState(tdto.getState());
        t.setDescription(tdto.getDescription());

        return t;
    }

}
