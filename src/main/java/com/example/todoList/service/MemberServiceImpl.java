package com.example.todoList.service;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.entity.TodoList;
import com.example.todoList.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    // 회원가입 메서드
    @Override
    public void signup(MemberDTO dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(encoder.encode(dto.getPassword()));
        repository.save(dtoToEntity(dto));
    }

    // Member에 있는 OneToMany 참조타입을 이용해서 멤버를 찾아 오면서 그 멤버의 투두리스트 모두 들고옴
    // 들고온 리스트를 화면에 전달해줄 dto가 담긴 리스트로 변환해서 전달
    @Override
    public List<TodoListDTO> getMyTodoList(String userId) {
        Function<TodoList,TodoListDTO> fn = (entity->(getMyList(entity)));
        List<TodoListDTO> dtoList = null;

        Optional<Member> optList = repository.findByUserId(userId);

        if(optList.isPresent()){
            Member memberEntity = optList.get();
            List<TodoList> todoEntity = memberEntity.getTodoList();
            dtoList = todoEntity.stream().map(fn).collect(Collectors.toList());
        }
        return dtoList;
    }


    //  ================================Override==================================
    // security에서 이용하는 메서드
    // username(userId)를 가지고 db에서 값을 찾아옴 // 자동으로 password를 비교해주는 기능이 있다
    @Override
    public Member loadUserByUsername(String userId) throws UsernameNotFoundException {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다!"));
    }


}
