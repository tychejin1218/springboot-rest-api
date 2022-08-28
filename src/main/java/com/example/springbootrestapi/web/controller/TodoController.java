package com.example.springbootrestapi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

  @GetMapping("/api/todo")
  public String getTodo() {
    return "todo";
  }
}
