package com.example.springbootrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

public class Todo {

  @Alias("TodoRequest")
  @Getter
  @Setter
  @ToString
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
  }

  @Alias("TodoResponse")
  @Getter
  @Setter
  @ToString
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {

    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
  }
}
