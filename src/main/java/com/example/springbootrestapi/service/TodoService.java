package com.example.springbootrestapi.service;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.mapper.TodoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class TodoService {

  private final TodoMapper todoMapper;

  /**
   * To-Do 조회
   */
  public List<Todo.Response> getTodos(Todo.Request todoRequest) {
    return todoMapper.getTodos(todoRequest);
  }

  /**
   * To-Do 상세 조회
   */
  public Todo.Response getTodoById(int id) {
    return todoMapper.getTodoById(id);
  }

  /**
   * To-Do 저장
   */
  public Todo.Response insertTodo(Todo.Request todoRequest) {

    Todo.Response todoResponse = Todo.Response.builder().build();

    int result = todoMapper.insertTodo(todoRequest);
    if (result > 0 && !ObjectUtils.isEmpty(todoRequest.getId())) {
      todoResponse = todoMapper.getTodoById(todoRequest.getId());
    }

    return todoResponse;
  }

  /**
   * To-Do 수정
   */
  public Todo.Response updateTodo(Todo.Request todoRequest) {

    Todo.Response todoResponse = Todo.Response.builder().build();

    int result = todoMapper.updateTodo(todoRequest);
    if (result > 0) {
      todoResponse = todoMapper.getTodoById(todoRequest.getId());
    }

    return todoResponse;
  }

  /**
   * To-Do 삭제
   */
  public Todo.Response deleteTodoById(int id) {

    Todo.Response todoResponse = Todo.Response.builder().build();

    int result = todoMapper.deleteTodoById(id);
    if (result > 0) {
      todoResponse = todoMapper.getTodoById(id);
    }

    return todoResponse;
  }
}