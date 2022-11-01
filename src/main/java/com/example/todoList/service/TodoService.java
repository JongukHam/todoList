package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.entity.TodoList;

import java.util.List;

public interface TodoService {

    // form에서 입력받은 dto를 posting(할일 등록) 할 때 저장할 entity로 변환
    default TodoList dtoToEntity(TodoListDTO dto){
        Member member = Member.builder().userId(dto.getWriter()).build();
        TodoList entity = TodoList.builder()
                .todo(dto.getTodo())
                .writer(member)
                .build();
        return entity;
    }

    void posting(TodoListDTO dto);

    void completeTodo(List<String> completedThings);

}
