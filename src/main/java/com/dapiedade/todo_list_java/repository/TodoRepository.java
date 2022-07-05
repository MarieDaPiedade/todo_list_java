package com.dapiedade.todo_list_java.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dapiedade.todo_list_java.model.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>{
    
    // find a todo by state
    public List<Todo> findAllByState(String state);

}
