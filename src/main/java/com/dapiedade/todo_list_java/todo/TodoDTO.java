package com.dapiedade.todo_list_java.todo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    @NotNull
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String state;
    
    private String description;
}
