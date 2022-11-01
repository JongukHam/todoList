package com.example.todoList.service;

import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.entity.TodoList;
import com.example.todoList.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoListRepository repository;


    // form에서 받은 값 db에 저장
    @Override
    public void posting(TodoListDTO dto) {
        TodoList realSave = dtoToEntity(dto);
        repository.save(realSave);
    }

    // 완료(체크)한 일 저장
    @Override
    public void completeTodo(List<String> completedThings) {
        if(!completedThings.isEmpty()){
            for(String thing : completedThings){
                Long tno = Long.parseLong(thing);
                Optional<TodoList> before = repository.findById(tno);
                if(before.isPresent()){
                    Member mem = Member.builder().userId(before.get().getWriter().getUserId()).build();
                    TodoList after = TodoList.builder()
                            .tno(before.get().getTno())
                            .todo(before.get().getTodo())
                            .done(!before.get().isDone())
                            .writer(mem)
                            .build();
                    repository.save(after);
                }
            }
        }
    }


}
