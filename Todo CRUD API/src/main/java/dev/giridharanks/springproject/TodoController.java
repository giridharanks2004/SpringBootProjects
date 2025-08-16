package dev.giridharanks.springproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo") // maping a common request to it's sub requests
public class TodoController {
    
    @Autowired
    public TodoService a;

    @PostMapping("/createtodo") // create a todo
    public ResponseEntity<Todo> create(@RequestBody Todo newEvent){
        ResponseEntity<Todo> response = new ResponseEntity<>(a.createTodo(newEvent),HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/getbyid") // get todo by id 
    public ResponseEntity<Todo> getbytoid(@RequestParam Integer id){
        try{
            return new ResponseEntity<>(a.getbyTodoid(id),HttpStatus.OK);
            
        } catch(RuntimeException exception){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } 
    }
    @GetMapping("/all") // read all todos
    ResponseEntity<List<Todo>> getAll(){
        return new ResponseEntity<>(a.getallTodos(),HttpStatus.OK);
    }

    @DeleteMapping("/delete") // delete a todo
    ResponseEntity<String> deletebyid(@RequestParam Integer id){
        try {
             a.deleTodo(id);
             return new ResponseEntity<>("deletion done",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("deletion not done",HttpStatus.NOT_FOUND);
        }
       
    }

    @PutMapping("/update") // update a todo
    ResponseEntity<Todo> updateTodo(@RequestBody Todo event){
        return new ResponseEntity<>(a.updateTodo(event),HttpStatus.OK);
    }

}
