package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.entity.TodoList;

import java.util.List;

public interface TodoService {

    default TodoListDTO entityToDto(TodoList entity){
        TodoListDTO dto = TodoListDTO.builder()
                .tno(entity.getTno())
                .done(entity.isDone())
                .todo(entity.getTodo())
                .regDate(entity.getRegDate())
                .comDate(entity.getComDate())
                .userid(entity.getMember().getUserid())
                .build();
        return dto;
    }

    default TodoList dtoToEntity(TodoListDTO dto){
        Member mem = Member.builder()
                .userid(dto.getUserid())
                .build();
        TodoList entity = TodoList.builder()
                .todo(dto.getTodo())
                .done(dto.isDone())
                .member(mem)
                .build();
        return entity;
    }

    default TodoList dtoToEntityForMod(TodoListDTO dto){
        Member mem = Member.builder()
                .userid(dto.getUserid())
                .build();
        TodoList entity = TodoList.builder()
                .tno(dto.getTno())
                .todo(dto.getTodo())
                .done(dto.isDone())
                .member(mem)
                .build();
        return entity;
    }

    // 모든 리스트 불러오기
    List<TodoListDTO> getList();
    // 할일 쓰기
    void writeTodo(TodoListDTO writeDTO);
    // 할일 완료로 수정
    void doneSomething(List<String> dto);


}
