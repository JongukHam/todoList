package com.example.todoList.service;

import com.example.todoList.dto.TodoListDTO;
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

    public List<TodoListDTO> getList(){
        Function<TodoList,TodoListDTO> fn = (todoList -> entityToDto(todoList));
        List<TodoList> enlist = repository.findAll();
        List<TodoListDTO> list = enlist.stream().map(fn).collect(Collectors.toList());
        return list;
    }

    public void writeTodo(TodoListDTO writeDTO){
        //Function<TodoListDTO,TodoList> fn = (todoList -> dtoToEntity(todoList));
        TodoList realSave = dtoToEntity(writeDTO);
        repository.save(realSave);
    }

    @Override
    public void doneSomething(List<String> doneList) {
        for(String done : doneList){
            Long tno = Long.parseLong(done);
            Optional<TodoList> bfTodo= repository.findById(tno);
            if(bfTodo.isPresent()){
                TodoListDTO afTodo= entityToDto(bfTodo.get());
                afTodo.setDone(!afTodo.isDone());
                repository.save(dtoToEntityForMod(afTodo));
            }
        }
    }
}
