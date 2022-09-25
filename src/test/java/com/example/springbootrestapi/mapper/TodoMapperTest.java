package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.domain.Todo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class TodoMapperTest {

  @Autowired
  TodoMapper todoMapper;

  @Disabled("TodoMapper_To-Do 조회")
  @Test
  void testGetTodos() {

    // Given
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Spring Cloud")
        .description("description")
        .build();

    // When
    List<Todo.Response> todoResponse = todoMapper.getTodos(todoRequest);

    // Then
    Assertions.assertEquals(2, todoResponse.size());
  }
}