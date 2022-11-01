package com.example.todoList.service;

import com.example.todoList.dto.TodoListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;
    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    public void getMyBoard(){
        List<TodoListDTO> myList = memberService.getMyTodoList("user1");
        if(myList.isEmpty()){
            System.out.println("리스트 비어있음");
        }else{
            for(TodoListDTO list : myList){
                System.out.println(list);
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void postingTest(){
        TodoListDTO testDto = TodoListDTO.builder()
                        .todo("it is for test2")
                        .writer("user1")
                        .build();
        todoService.posting(testDto);
    }



}
