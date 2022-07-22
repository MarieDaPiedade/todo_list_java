package com.dapiedade.todo_list_java.todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.SneakyThrows;

public class TodoDTOTest {

    /**
     * Vérifie qu'il n'y a pas d'exception si le todoDTO est valide
     * 
     * @throws Exception
     */
    @Test
    public void test_givenValidDto_whenValidated_thenNoValidationError() throws Exception {
        TodoDTO todoDto = getValidTodoDto();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        Set<ConstraintViolation<TodoDTO>> constraintViolations = validator.validate(todoDto);

        assertThat(constraintViolations.size()).isZero();
    }

    static Stream<Arguments> provideFieldAndInvalidValue() {
        return Stream.of(
                Arguments.of("title", null),
                Arguments.of("state", null));
    }

    /**
     * Vérifie l'exception si le todoDTO est invalide
     * 
     * @param fieldName
     * @param invalidValue
     */
    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideFieldAndInvalidValue")
    void testInvalidDto(String fieldName, Object invalidValue) {
        TodoDTO todoDto = getValidTodoDto();

        Field field = TodoDTO.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(todoDto, invalidValue);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<TodoDTO>> constraintViolations = validator.validate(todoDto);
        assertThat(constraintViolations.size()).isOne();
    }

    /**
     * Création d'un todoDTO valide
     * 
     * @return
     */
    private TodoDTO getValidTodoDto() {
        return new TodoDTO(80, "Title Test", "Todo", "Description Test");
    }
}
