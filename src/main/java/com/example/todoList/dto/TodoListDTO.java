package com.example.todoList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoListDTO {

    private Long tno;
    private boolean done;
    private String todo;
    private LocalDateTime regDate,comDate;
    private String userid;

}
