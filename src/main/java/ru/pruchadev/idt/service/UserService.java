package ru.pruchadev.idt.service;


import org.springframework.stereotype.Service;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(String userName){
        Optional<User> user=userRepository.getByUserName(userName);
        return user.isEmpty()?null:user.get();
    }

    public List<User> getAll(){
        List<User> users=new ArrayList<>();
         userRepository.findAll().iterator().forEachRemaining(users::add);
         return users;
    }
}
