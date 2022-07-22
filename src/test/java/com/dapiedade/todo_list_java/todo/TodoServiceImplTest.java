package com.dapiedade.todo_list_java.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceImplTest {

    @Autowired
    TodoServiceImpl todoService = new TodoServiceImpl();

    @Test
    void testGetWithSuccess() {
        Todo todo = todoService.get(1);
        assertEquals("Title todo numéro 1", todo.getTitle());
    }

    @Test
    void testGetWithNullPointerException() throws Exception {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            todoService.get(10);
        });
        assertEquals("La Todo demandée n'existe pas !", exception.getMessage());
    }

    @Test
    void testGetAll() {
        List<Todo> todos = todoService.getAll();
        assertEquals(5, todos.size());
    }

    @Test
    void saveTest() {
        Todo todo = getTodoWithTodoState();
        todoService.save(todo);
        todoService.get(5);
        assertEquals("title", todo.getTitle());
    }

    @Test
    void testUpdateState() {
        Todo todo = getTodoWithCompletedState();
        Todo todoToUpdate = todoService.get(1);
        todoService.updateState(todoToUpdate.getId(), todo);
        assertEquals("Completed", todoToUpdate.getState());
    }

    @Test 
    void testTodoToDto() {
        Todo todo = todoService.get(1);
        TodoDTO tdto = todoService.todoToDto(todo);
        assertEquals(todo.getId(), tdto.getId());
    }

    @Test
    void testDtoToTodo() {
        TodoDTO tdto = new TodoDTO();
        tdto.setId(8);
        Todo todo = todoService.dtoToTodo(tdto);
        assertEquals(tdto.getId(), todo.getId());
    }

    /**
     * Retourne une nouvelle Todo avec le state "Todo"
     * 
     * @return
     */
    private Todo getTodoWithTodoState() {
        Todo todo = new Todo();
        todo.setId(5);
        todo.setTitle("title");
        todo.setState("Todo");
        todo.setDescription("description");
        return todo;
    }

    /**
     * Retourne une nouvelle Todo avec le state "Completed"
     * 
     * @return
     */
    private Todo getTodoWithCompletedState() {
        Todo todo = new Todo();
        todo.setId(6);
        todo.setTitle("title 6");
        todo.setState("Completed");
        todo.setDescription("description 6");
        return todo;
    }

}
