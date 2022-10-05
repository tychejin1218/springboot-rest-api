package com.example.springbootrestapi.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.mapper.TodoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
class TodoControllerTest {

  @Resource
  TodoMapper todoMapper;

  @Resource
  MockMvc mockMvc;

  @Resource
  ObjectMapper objectMapper;

  @Transactional
  @DisplayName("getTodos_To-Do 목록 조회")
  @Test
  void testGetTodos() throws Exception {

    // Given
    insertTodo("Title Junit Test Insert 01", "Description Junit Test Insert 01", false);
    insertTodo("Title Junit Test Insert 02", "Description Junit Test Insert 02", true);
    insertTodo("Title Junit Test Insert 03", "Description Junit Test Insert 03", false);
    insertTodo("Title Junit Test Insert 04", "Description Junit Test Insert 04", true);
    insertTodo("Title Junit Test Insert 05", "Description Junit Test Insert 05", false);

    String url = "/api/todos";
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Title Junit Test Insert")
        .description("Description Junit Test Insert")
        .completed(true)
        .build();

    // When
    ResultActions resultActions = mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todoRequest))
    );

    // Then
    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(2)))
        .andDo(print());
  }

  @Transactional
  @DisplayName("getTodoById_To-Do 상세 조회")
  @Test
  void testGetTodoById() throws Exception {

    // Given
    String title = "Title Junit Test Insert";
    String description = "Description Junit Test Insert";
    Boolean completed = false;
    int insertId = insertTodo(title, description, completed);

    String url = "/api/todo/" + insertId;

    // When
    ResultActions resultActions = mockMvc.perform(
        get(url)
            .contentType(MediaType.APPLICATION_JSON)
    );

    // Then
    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", equalTo(title)))
        .andExpect(jsonPath("$.description", equalTo(description)))
        .andExpect(jsonPath("$.completed", equalTo(completed)))
        .andDo(print());
  }

  @Transactional
  @DisplayName("insertTodo_To-Do 저장")
  @Test
  void testInsertTodo() throws Exception {

    // Given
    String url = "/api/todo";
    Todo.Request todoRequest = Todo.Request.builder()
        .title("Title Junit Test Insert")
        .description("Description Junit Test Insert")
        .completed(true)
        .build();

    // When
    ResultActions resultActions = mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todoRequest))
    );

    // Then
    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", equalTo(todoRequest.getTitle())))
        .andExpect(jsonPath("$.description", equalTo(todoRequest.getDescription())))
        .andExpect(jsonPath("$.completed", equalTo(todoRequest.getCompleted())))
        .andDo(print());
  }

  @Transactional
  @DisplayName("updateTodo_To-Do 수정")
  @Test
  void testUpdateTodo() throws Exception {

    // Given
    String title = "Title Junit Test Insert";
    String description = "Description Junit Test Insert";
    boolean completed = false;
    int insertId = insertTodo(title, description, completed);

    String url = "/api/todo";
    Todo.Request todoRequest = Todo.Request.builder()
        .id(insertId)
        .title("Title Junit Test Update")
        .description("Description Junit Test Update")
        .completed(true)
        .build();

    // When
    ResultActions resultActions = mockMvc.perform(
        put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todoRequest))
    );

    // Then
    resultActions
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", equalTo(todoRequest.getTitle())))
        .andExpect(jsonPath("$.description", equalTo(todoRequest.getDescription())))
        .andExpect(jsonPath("$.completed", equalTo(todoRequest.getCompleted())))
        .andDo(print());
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