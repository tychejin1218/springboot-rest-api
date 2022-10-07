package com.example.springbootrestapi.web.controller;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.service.TodoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
    log.info("todoRequest:[{}]", todoRequest.toString());
    return todoService.getTodos(todoRequest);
  }

  /**
   * To-Do 조회
   */
  @GetMapping(
      value = "/api/todo/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Todo.Response getTodoById(
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable int id) {
    log.info("id:[{}]", id);
    return todoService.getTodoById(id);
  }

  /**
   * To-Do 저장
   */
  @PostMapping(
      value = "/api/todo",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Todo.Response insertTodo(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestBody Todo.Request todoRequest) {
    log.info("todoRequest:[{}]", todoRequest.toString());
    return todoService.insertTodo(todoRequest);
  }

  /**
   * To-Do 수정
   */
  @PutMapping(
      value = "/api/todo",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Todo.Response updateTodo(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestBody Todo.Request todoRequest) {
    log.info("todoRequest:[{}]", todoRequest.toString());
    return todoService.updateTodo(todoRequest);
  }

  /**
   * To-Do 삭제
   */
  @DeleteMapping(
      value = "/api/todo/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Todo.Response deleteTodo(
      HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable int id) {
    log.info("id:[{}]", id);
    return todoService.deleteTodoById(id);
  }
}