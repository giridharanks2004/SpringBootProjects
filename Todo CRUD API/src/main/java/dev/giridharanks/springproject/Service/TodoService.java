package dev.giridharanks.springproject.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.giridharanks.springproject.Model.Todo;
import dev.giridharanks.springproject.Repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todo_ob;
    public Todo createTodo(Todo newTodo){ //create
        return todo_ob.save(newTodo);
    }
    public Todo getbyTodoid(Integer id){ //read by id
        return todo_ob.findById(id).orElseThrow(()->new RuntimeException("todo not found"));
    }
    public List<Todo> getallTodos(){ // read
        return todo_ob.findAll();
    }
    public void deleTodo(Integer id){ // delete 
        todo_ob.delete(getbyTodoid(id));
    }
    public Todo updateTodo(Todo event){ //update
        return todo_ob.save(event);
    }
}
