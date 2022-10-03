package com.example.springbootrestapi.service;

import com.example.springbootrestapi.domain.Todo;
import com.example.springbootrestapi.mapper.TodoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
  public int insertTodo(Todo.Request todoRequest) {
    return todoMapper.insertTodo(todoRequest);
  }

  /**
   * To-Do 수정
   */
  public int updateTodo(Todo.Request todoRequest) {
    return todoMapper.updateTodo(todoRequest);
  }

  /**
   * To-Do 삭제
   */
  public int deleteTodoById(int id) {
    return todoMapper.deleteTodoById(id);
  }
}