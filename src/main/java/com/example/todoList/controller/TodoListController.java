package com.example.todoList.controller;

import com.example.todoList.dto.MemberDTO;
import com.example.todoList.dto.TodoListDTO;
import com.example.todoList.entity.Member;
import com.example.todoList.service.MemberService;
import com.example.todoList.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class TodoListController {

    public final MemberService memberService;
    public final TodoService todoService;

//===========================GET===========================

    @GetMapping({"/member/login","/"})
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("/member/signup")
    public String signUpPage(){
        return "signUpPage";
    }

    @GetMapping("/todo/list")
    public String todoListPage(Model model, @AuthenticationPrincipal Member loginUser){
        model.addAttribute("todoList", memberService.getMyTodoList(loginUser.getUserId()));
        return "todoListPage";
    }

    @GetMapping("/denied")
    public String deniedPage(){
        return "deniedPage";
    }




//===========================POST===========================

    @PostMapping("/member/signup")
    public String signup(MemberDTO signUpUserDto){
        memberService.signup(signUpUserDto);
        return "redirect:/member/login";
    }

    @PostMapping("/todo/posting")
    public String posting(TodoListDTO postingData,@AuthenticationPrincipal Member loginUser){
        postingData.setWriter(loginUser.getUserId());
        postingData.setDone(false);
        todoService.posting(postingData);
        return "redirect:/todo/list";
    }

    @PostMapping("/todo/todoDone")
    public String todoDone(@RequestParam List<String> checked){ // form의 name이 checked인 input이 여러개로 넘어올 때 자동으로 List타입으로 받아짐
        todoService.completeTodo(checked);
        return "redirect:/todo/list";
    }
}
