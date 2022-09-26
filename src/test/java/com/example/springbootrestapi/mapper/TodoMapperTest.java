package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.domain.Todo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class TodoMapperTest {

  @Autowired
  TodoMapper todoMapper;

  @Disabled("getTodos_To-Do 목록 조회")
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
    Assertions.assertTrue(!todoResponse.isEmpty());
  }

  @Disabled("insertTodo_To-Do 저장")
  @Test
  void testInsertTodo() {

    // Given
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Spring Boot")
        .description("description01")
        .completed(false)
        .build();

    // When
    int result = todoMapper.insertTodo(todoRequest);

    // Then
    Assertions.assertEquals(1, result);
  }

  /**
   * To-Do 저장
   */
  int insertTodo(
      String title,
      String description,
      Boolean completed) {
    return todoMapper.insertTodo(Todo.Request.builder()
        .title(title)
        .description(description)
        .completed(completed)
        .build());
  }
}