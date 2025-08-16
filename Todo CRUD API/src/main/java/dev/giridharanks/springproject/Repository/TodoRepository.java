package dev.giridharanks.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.giridharanks.springproject.Model.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

}
