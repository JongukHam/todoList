package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    public String login(MemberDTO dto){
        if(dto.getPassword() ==null || dto.getUserid()==null){
            return "아이디나 패스워드는 공백일 수 없습니다.";
        }

        Optional<Member> MemberET = repository.findByUserid(dto.getUserid());
        MemberDTO tryLogin;

        if(MemberET.isPresent()){
            tryLogin = entityToDto(MemberET.get());
        }else{
            return "아이디를 찾을 수 없습니다.";
        }

        if(tryLogin.getUserid().equals(dto.getUserid()) && tryLogin.getPassword().equals(dto.getPassword())){
            return "로그인 성공";
        }
        return "로그인 실패";
    }
}
