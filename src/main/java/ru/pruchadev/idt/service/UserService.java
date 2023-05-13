package ru.pruchadev.idt.service;


import org.springframework.stereotype.Service;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        return userRepository.save(user);
    }
}
