package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private static List<todos> todos = new ArrayList<>();
    private static int todoCount=0;

    static {
        todos.add(new todos(++todoCount, "in28minutes","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new todos(++todoCount, "in28minutes","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new todos(++todoCount, "in28minutes","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }

    public List<todos> findByUsername(String username){
        return todos;
    }

    public void addTodo(String username,String Description,LocalDate targetDate,boolean done){
        todos todos1 = new todos(++todoCount,username,Description,targetDate,done);
        todos.add(todos1);
    }

    public void deleteByID(int id) {
        Predicate<? super todos> predicate = todos -> todos.getId() == id;
        todos.removeIf(predicate);
    }
}