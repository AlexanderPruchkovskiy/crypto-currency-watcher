package ru.pruchadev.idt.repository;


import org.springframework.data.repository.CrudRepository;
import ru.pruchadev.idt.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {

}
