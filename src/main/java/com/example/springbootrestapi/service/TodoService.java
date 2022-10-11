package com.example.springbootrestapi.service;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.mapper.TodoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class TodoService {

  private final TodoMapper todoMapper;

  /**
   * To-Do 조회
   */
  @Transactional(readOnly = true)
  public List<Todo.Response> getTodos(Todo.Request todoRequest) {
    return todoMapper.getTodos(todoRequest);
  }

  /**
   * To-Do 상세 조회
   */
  @Transactional(readOnly = true)
  public Todo.Response getTodoById(int id) {
    return todoMapper.getTodoById(id);
  }

  /**
   * To-Do 저장
   */
  @Transactional
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
  @Transactional
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
  @Transactional
  public Todo.Response deleteTodoById(int id) {

    Todo.Response todoResponse = Todo.Response.builder().build();

    int result = todoMapper.deleteTodoById(id);
    if (result > 0) {
      todoResponse = todoMapper.getTodoById(id);
    }

    return todoResponse;
  }

  /**
   * To-Do 저장 시 title이 #으로 시작하는 경우 RuntimeException 발생
   */
  @Transactional
  public int insertTodosFailed(List<Todo.Request> todoRequests) {

    int result = 0;

    for (Todo.Request todoRequest : todoRequests) {
      if (todoRequest.getTitle().startsWith("#")) {
        throw new RuntimeException("title이 #으로 시작");
      }
      result += todoMapper.insertTodo(todoRequest);
    }

    return result;
  }
}