package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class toDoController {
    private TodoService todoService;

    public toDoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAlltoDos(ModelMap model){
        List<todos> todos = todoService.findByUsername("in28minutes");
        model.addAttribute("todos",todos);
        return "listTodos";
    }


    //new Add to do handling GET,POST
    @RequestMapping(value="add-todo",method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        String username = (String) model.get("name");
        todos todos =new todos(0,username,"",LocalDate.now().plusYears(1),false);
        model.put("todos",todos);
        return "todo";
    }

    @RequestMapping(value="add-todo",method =RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid todos todos, BindingResult result){
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoService.addTodo(username,todos.getDescription(),LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";

    }

    @RequestMapping(value="delete-todo",method=RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){
        todoService.deleteByID(id);
        return "redirect:list-todos";

    }

    @RequestMapping(value="update-todo",method= RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
        todos todos=todoService.findById(id);
        model.addAttribute("todos",todos);
        return "todo";
    }
    @RequestMapping(value="update-todo",method =RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid todos todos, BindingResult result){
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todos.setUsername(username);
        todoService.updateTodo(todos);
        return "redirect:list-todos";

    }
}
