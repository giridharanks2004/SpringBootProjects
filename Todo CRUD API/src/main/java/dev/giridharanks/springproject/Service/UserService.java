package dev.giridharanks.springproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.giridharanks.springproject.Model.User;
import dev.giridharanks.springproject.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userDB;

    public User createuser(User user){
        return userDB.save(user);
    }
    public User getUserById(Integer id){
        return userDB.findById(id).orElse(null);
    }
    

}
