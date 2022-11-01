package com.example.todoList.dto;

import com.example.todoList.entity.TodoList;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class MemberDTO {

    private String userId;
    private String password;
    private String auth;
    private LocalDateTime regDate,comDate;
    private List<TodoListDTO> todoList;
}
