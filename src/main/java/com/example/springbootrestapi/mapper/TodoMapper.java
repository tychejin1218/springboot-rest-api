package com.example.springbootrestapi.mapper;

import com.example.springbootrestapi.domain.Todo;
import java.util.List;

public interface TodoMapper {

  /** To-Do 조회 */
  List<Todo.Response> getTodos(Todo.Request todoRequest);
}
