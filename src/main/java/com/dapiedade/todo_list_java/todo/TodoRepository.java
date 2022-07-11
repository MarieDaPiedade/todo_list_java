package com.dapiedade.todo_list_java.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>{
    
    // find a todo by state
    public List<Todo> findAllByState(String state);

}
