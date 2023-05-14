package ru.pruchadev.idt.repository;


import org.springframework.data.repository.CrudRepository;
import ru.pruchadev.idt.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {


    Optional<User> getByUserName(String symbol);
}
