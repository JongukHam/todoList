package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    void login() {
    }

    @Test
    void signup() {
        IntStream.rangeClosed(1,10).forEach(i->{
            MemberDTO dto = MemberDTO.builder()
                    .userid("Member"+i)
                    .password("1234")
                    .build();
            service.signup(dto);
        });
    }
}