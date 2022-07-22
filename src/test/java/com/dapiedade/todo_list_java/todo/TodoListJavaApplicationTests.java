package com.dapiedade.todo_list_java.todo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoListJavaApplicationTests {

	@Autowired
	private TodoController controller;

	/**
	 * Vérifie la présence du TodoController
	 * 
	 * @throws Exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
