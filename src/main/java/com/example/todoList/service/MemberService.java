package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;

public interface MemberService {

    //default로 entity dto 변환
    default MemberDTO entityToDto(Member entity){
        MemberDTO dto = MemberDTO.builder()
                .userid(entity.getUserid())
                .password(entity.getPassword())
                .regDate(entity.getRegDate())
                .comDate(entity.getComDate())
                .build();
        return dto;
    }
    default Member dtoToEntity(MemberDTO dto){
        Member entity = Member.builder()
                .userid(dto.getUserid())
                .password(dto.getPassword())
                .build();
        return entity;
    }

    String login(MemberDTO dto);

    void signup(MemberDTO dto);

}
