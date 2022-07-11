package com.dapiedade.todo_list_java.todo;

import lombok.Data;

@Data
public class TodoDTO {

    private long id;
    private String title;
    private String state;
    private String description;
}
