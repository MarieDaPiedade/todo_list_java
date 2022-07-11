package com.dapiedade.todo_list_java;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dapiedade.todo_list_java.todo.Todo;
import com.dapiedade.todo_list_java.todo.TodoRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!alreadySetup) {

            for (int i = 1; i < 4; i++) {
                Todo todo = new Todo();
                todo.setTitle("Title todo numéro " + i);
                todo.setState("Todo");
                todo.setDescription("Description de la todo numéro " + i
                        + " : Lorem ipsum dolor sit amet. Qui iure numquam a aliquam voluptatem ex minus dolores aut nulla omnis et reprehenderit dolorum. Et voluptatem consequatur et nobis blanditiis et quia asperiores! ");

                todoRepository.save(todo);
            }
        }
    }
}
