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

  public List<Todo.Response> getTodos(Todo.Request todoRequest) {
    return todoMapper.getTodos(todoRequest);
  }
}
