package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.domain.Todo;
import java.util.List;

public interface TodoMapper {

  /**
   * To-Do 목록 조회
   */
  List<Todo.Response> getTodos(Todo.Request todoRequest);

  /**
   * To-Do 상세 조회
   */
  Todo.Response getTodoById(int id);

  /**
   * To-Do 저장
   */
  int insertTodo(Todo.Request todoRequest);

  /**
   * To-Do 수정
   */
  int updateTodo(Todo.Request todoRequest);

  /**
   * To-Do 삭제
   */
  int deleteTodoById(int id);
}
