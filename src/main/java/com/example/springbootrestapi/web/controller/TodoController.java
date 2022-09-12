package com.example.springbootrestapi.web.controller;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.service.TodoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TodoController {

  private final TodoService todoService;

  /**
   * To-Do 조회
   */
  @PostMapping(
      value = "/api/todos",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Todo.Response> getTodos(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestBody Todo.Request todoRequest) {
    return todoService.getTodos(todoRequest);
  }
}