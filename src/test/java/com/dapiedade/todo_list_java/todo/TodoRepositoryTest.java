package com.dapiedade.todo_list_java.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testFindById() {
        Todo todo = getTodo();
        todoRepository.save(todo);
        Todo result = todoRepository.findById(todo.getId()).get();
        assertEquals(todo.getId(), result.getId());
    }

    @Test
    public void testFindAll() {
        List<Todo> result = new ArrayList<>();
        todoRepository.findAll().forEach(e -> result.add(e));
        assertEquals(5, result.size());
    }

    @Test
    public void testSave() {

        Todo todo = getTodo();
        todoRepository.save(todo);
        Todo found = todoRepository.findById(todo.getId()).get();
        assertEquals(todo.getId(), found.getId());
    }

    /**
     * Retourne une nouvelle Todo
     * 
     * @return
     */
    private Todo getTodo() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("title");
        todo.setState("Todo");
        todo.setDescription("description");
        return todo;
    }

}
