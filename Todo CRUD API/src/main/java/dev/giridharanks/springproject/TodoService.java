package dev.giridharanks.springproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todo_ob;
    public Todo createTodo(Todo newTodo){
        return todo_ob.save(newTodo);
    }
    public Todo getbyTodoid(Integer id){
        return todo_ob.findById(id).orElseThrow(()->new RuntimeException("todo not found"));
    }
    public List<Todo> getallTodos(){
        return todo_ob.findAll();
    }
    public void deleTodo(Integer id){
        todo_ob.delete(getbyTodoid(id));
    }
    public Todo updateTodo(Todo event){
        return todo_ob.save(event);
    }
}
