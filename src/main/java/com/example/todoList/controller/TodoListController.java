package com.example.todoList.controller;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/todo","/"})
@RequiredArgsConstructor
public class TodoListController {

    public final MemberService service;

    // 로그인 페이지
    @GetMapping
    public String welcomePage(){
        return "homepage";
    }
    // 로그인 성공하고 리스트 페이지
    @GetMapping("/todolist")
    public String todoListPage(){
        return "todolist";
    }
    // 홈에서 로그인 했을 때
    @PostMapping("/login")
    public String tryLogin(MemberDTO dto, RedirectAttributes redirectAttributes){
        String loginState = service.login(dto);
        if(loginState.equals("로그인 성공")){
            return "redirect:/todo/todolist";
        }else{
            redirectAttributes.addAttribute("loginState",loginState);
            return "redirect:/todo";
        }
    }



}
