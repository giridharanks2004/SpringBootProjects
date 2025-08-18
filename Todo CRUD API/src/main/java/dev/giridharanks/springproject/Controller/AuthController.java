package dev.giridharanks.springproject.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.giridharanks.springproject.Model.User;
import dev.giridharanks.springproject.Repository.UserRepository;
import dev.giridharanks.springproject.Service.UserService;
import dev.giridharanks.springproject.uitils.Tokenizer;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userDB;
    @Autowired
    private UserService user_service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Tokenizer tokenCreator;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));
        if(userDB.findByEmail(email).isPresent()){
            return new ResponseEntity<>("user already exists",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(user_service.createuser(User.builder().email(email).password(password).build()),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        var userOptional = userDB.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("not found please register",HttpStatus.UNAUTHORIZED);
        }
        User user = userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity<>("incorrect password",HttpStatus.UNAUTHORIZED);
        }
        String token = tokenCreator.GenerateToken(email);
        return new ResponseEntity<>(Map.of("token",token),HttpStatus.OK);
    }
}
