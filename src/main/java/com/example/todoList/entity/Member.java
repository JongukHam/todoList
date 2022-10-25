package com.example.todoList.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends CreateDate{

    @Id
    @Column(length = 20, nullable = false)
    private String userid;

    @Column(length = 20, nullable = false)
    private String password;

//    @OneToMany(mappedBy = "member")
//    private List<TodoList> todoList = new ArrayList<>();
}
