package com.futark.cros2.controller;

import com.futark.cros2.entity.Todo;
import com.futark.cros2.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService todoService;


    @PostMapping("/save")
    ResponseEntity<Todo> saveControl(@RequestBody Todo todo){
        return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getControl(@PathVariable Long id){
        return new ResponseEntity<Todo>(todoService.getTodo(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity <List<Todo>> getControls(){
        return new ResponseEntity<List<Todo>>(todoService.getTodos(),HttpStatus.OK);
    }


    @DeleteMapping("/del/{id}")
    ResponseEntity <Boolean> delControl(@PathVariable Long id ){
        return new ResponseEntity<>(todoService.delTodo(id),HttpStatus.OK);
    }

}
