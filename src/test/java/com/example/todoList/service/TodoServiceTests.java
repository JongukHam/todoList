package com.example.todoList.service;

import com.example.todoList.dto.TodoListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void list다가져오기(){
        List<TodoListDTO> result = todoService.getList();
        result.forEach(i->{
            System.out.println(i);
        });
    }

    @Test
    public void putDummies(){
        IntStream.rangeClosed(1,10).forEach(i->{
            TodoListDTO dto = TodoListDTO.builder()
                    .userid("Member"+i)
                    .todo("할거 ="+i)
                    .build();
            todoService.writeTodo(dto);
        });

    }

}
