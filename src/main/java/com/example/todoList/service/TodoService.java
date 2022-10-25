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

    List<TodoListDTO> getList();
    void writeTodo(TodoListDTO writeDTO);
}
