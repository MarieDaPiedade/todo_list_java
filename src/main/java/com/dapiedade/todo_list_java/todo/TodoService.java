package com.dapiedade.todo_list_java.todo;

import java.util.List;

public interface TodoService {

    /**
     * Récupére une Todo en particulier
     * 
     * @param id
     * @return
     */
    public Todo get(long id);

    /**
     * Récupére la liste de l'ensemble des todos
     * 
     * @return
     */
    public List<Todo> getAll();

    /**
     * Enregistre une todo
     * 
     * @param todo
     * @return
     */
    public long save(Todo todo);

    /**
     * Met à jour l'état d'une todo
     * 
     * @param id
     * @param todo
     * @return
     */
    public Todo updateState(long id, Todo todo);

    /**
     * Réalise la conversion d'un objet Todo en objet TodoDTO
     * 
     * @param todo
     * @return
     */
    public TodoDTO todoToDto(Todo todo);

    /**
     * Réalise la conversion d'un objet TodoDTO en objet Todo
     * 
     * @param tdto
     * @return
     */
    public Todo dtoToTodo(TodoDTO tdto);
}
