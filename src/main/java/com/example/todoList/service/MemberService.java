package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.entity.TodoList;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface MemberService extends UserDetailsService {

    // form에서 받은 dto를 회원가입 할 때 사용할 entity로 변환
    default Member dtoToEntity(MemberDTO dto){
        Member entity = Member.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .auth(dto.getAuth())
                .build();
        return entity;
    }

    // todoList(Entity)를 view단으로 전달해줄 dto로 변환
    default TodoListDTO getMyList(TodoList entity){
        TodoListDTO dto = TodoListDTO.builder()
                .tno(entity.getTno())
                .todo(entity.getTodo())
                .done(entity.isDone())
                .writer(entity.getWriter().getUserId())
                .regDate(entity.getRegDate())
                .comDate(entity.getComDate())
                .build();
        return dto;
    }

    void signup(MemberDTO dto);

    List<TodoListDTO> getMyTodoList(String userId);
}
