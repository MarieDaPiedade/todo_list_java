package com.dapiedade.todo_list_java.todo;

import static org.junit.Assert.assertSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Vérifie que l'on peut récupérer la liste des todos
     * 
     * @throws Exception
     */
    @Test
    void testGetAllTodos() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk());
    }

    /**
     * Vérifie que l'on peut récupérer une Todo qui existe
     * 
     * @throws Exception
     */
    @Test
    void testGetATodoThatExists() throws Exception {
        mockMvc.perform(get("/api/get/1"))
                .andExpect(status().isOk());
    }

    /**
     * Vérifie que l'on ne peut pas enregistrer une nouvelle Todo si des champs sont
     * nuls/invalides
     * 
     * @throws Exception
     */
    @Test
    void testGivenAnInvalidTodoDtoShouldThrowError() throws Exception {
        TodoDTO todoDto = new TodoDTO(90, null, null, "description");
        mockMvc.perform(post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(todoDto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    private String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Vérifie la mise à jour du state d'une todo
     * 
     * @throws Exception
     */
    @Test
    void testUpdateStateTodo() throws Exception {
        TodoDTO todoDto = new TodoDTO(3, "Title todo 3", "Completed", "Description todo 3");
        mockMvc.perform(put("/api/update/" + todoDto.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(todoDto)))
                .andExpect(status().isOk());

        assertSame("Completed", todoDto.getState());
    }

}
