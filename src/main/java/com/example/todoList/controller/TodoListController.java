package com.example.todoList.controller;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.service.MemberService;
import com.example.todoList.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping({"/todo","/"})
@RequiredArgsConstructor
@Log4j2
public class TodoListController {

    public final MemberService memberService;
    public final TodoService todoService;

    // 로그인 페이지
    @GetMapping
    public String welcomePage(){
        return "homepage";
    }

    // 로그인 성공하고 리스트 페이지
    @GetMapping("/todolist")
    public String todoListPage(Model model){
        List<TodoListDTO> allList = todoService.getList();
        model.addAttribute("allList", allList);
        return "todolist";
    }

    // 홈에서 로그인 했을 때
    @PostMapping("/login")
    public String tryLogin(MemberDTO dto, RedirectAttributes redirectAttributes){
        String loginState = memberService.login(dto);
        if(loginState.equals("로그인 성공")){
            return "redirect:/todo/todolist";
        }else{
            redirectAttributes.addAttribute("loginState",loginState);
            return "redirect:/todo";
        }
    }

    @PostMapping("/doneSomething")
    public String doneSomething(@RequestParam List<String> checked){
            todoService.doneSomething(checked);
            return "redirect:/todo/todolist";
    }


}
