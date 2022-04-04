package com.futark.cros2.service;

import com.futark.cros2.entity.Todo;
import com.futark.cros2.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepo todoRepo;


    public Todo saveTodo(Todo todo){
        if(todo.getId()==null){
            todo.setCreation(new Date());
        }
        else{
            todo.setModif(new Date());
        }
        return todoRepo.save(todo);
    }

    public Todo getTodo(Long id){
        return todoRepo.findById(id).get();
    }

    public List<Todo> getTodos(){
//        return todoRepo.findAllByOrderByPriorityDscCreationAsc();
        return todoRepo.findAll();
    }


    public Boolean delTodo(Long id){
        try {
            todoRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
