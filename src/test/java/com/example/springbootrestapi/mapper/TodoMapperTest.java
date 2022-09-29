package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.domain.Todo;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
class TodoMapperTest {

  @Resource
  TodoMapper todoMapper;

  @Transactional
  @DisplayName("getTodos_To-Do 목록 조회")
  @Test
  void testGetTodos() {

    // Given
    insertTodo("Title Junit Test Insert 01", "Description Junit Test Insert 01", false);
    insertTodo("Title Junit Test Insert 02", "Description Junit Test Insert 02", true);
    insertTodo("Title Junit Test Insert 03", "Description Junit Test Insert 03", false);
    insertTodo("Title Junit Test Insert 04", "Description Junit Test Insert 04", true);
    insertTodo("Title Junit Test Insert 05", "Description Junit Test Insert 05", false);
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Title Junit Test Insert")
        .description("Description Junit Test Insert")
        .completed(true)
        .build();

    // When
    List<Todo.Response> todoResponses = todoMapper.getTodos(todoRequest);

    // Then
    Assertions.assertTrue(!todoResponses.isEmpty());
  }

  @Transactional
  @DisplayName("getTodoById_To-Do 상세 조회")
  @Test
  void testGetTodoById() {

    // Given
    int insertId = insertTodo("Title Junit Test Insert", "Description Junit Test Insert", false);

    // When
    Todo.Response todoResponse = todoMapper.getTodoById(insertId);

    // Then
    Assertions.assertEquals(insertId, todoResponse.getId());
  }

  @Transactional
  @DisplayName("insertTodo_To-Do 저장")
  @Test
  void testInsertTodo() {

    // Given
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Title Junit Test Insert")
        .description("Description Junit Test Insert")
        .completed(false)
        .build();

    // When
    todoMapper.insertTodo(todoRequest);

    // Then
    Todo.Response todoResponse = todoMapper.getTodoById(todoRequest.getId());
    Assertions.assertEquals(todoRequest.getTitle(), todoResponse.getTitle());
    Assertions.assertEquals(todoRequest.getDescription(), todoResponse.getDescription());
    Assertions.assertEquals(todoRequest.getCompleted(), todoResponse.getCompleted());
  }

  @Transactional
  @DisplayName("updateTodo_To-Do 수정")
  @Test
  void testUpdateTodo() {

    // Given
    int insertId = insertTodo("Title Junit Test Insert", "Description Junit Test Insert", false);
    Todo.Request todoRequest = Todo.Request.builder()
        .id(insertId)
        .title("Title Junit Test Update")
        .description("Description Junit Test Update")
        .completed(true)
        .build();

    // When
    todoMapper.updateTodo(todoRequest);

    // Then
    Todo.Response todoResponse = todoMapper.getTodoById(todoRequest.getId());
    Assertions.assertEquals(todoRequest.getTitle(), todoResponse.getTitle());
    Assertions.assertEquals(todoRequest.getDescription(), todoResponse.getDescription());
    Assertions.assertEquals(todoRequest.getCompleted(), todoResponse.getCompleted());
  }

  @Transactional
  @DisplayName("deleteTodoById_To-Do 삭제")
  @Test
  void testDeleteTodoById() {

    // Given
    int insertId = insertTodo("Title Junit Test Insert", "Description Junit Test Insert", false);

    // When
    todoMapper.deleteTodoById(insertId);

    // Then
    Todo.Response todoResponse = todoMapper.getTodoById(insertId);
    Assertions.assertNull(todoResponse);
  }

  /**
   * To-Do 저장
   */
  int insertTodo(
      String title,
      String description,
      Boolean completed) {

    Todo.Request todoRequest = Todo.Request.builder()
        .title(title)
        .description(description)
        .completed(completed)
        .build();

    todoMapper.insertTodo(todoRequest);

    return todoRequest.getId();
  }
}