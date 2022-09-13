package com.example.springbootrestapi.web.controller;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.service.TodoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TodoController {

  private final TodoService todoService;

  /** To-Do 조회 */
  @PostMapping(
      value = "/api/todos",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Todo.Response> getTodos(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestBody Todo.Request todoRequest) {
    log.info("todoRequest:[{}]", todoRequest.toString());
    return todoService.getTodos(todoRequest);
  }
}