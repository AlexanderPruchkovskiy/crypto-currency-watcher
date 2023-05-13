package ru.pruchadev.idt.web.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.service.UserService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = RestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    public static final String REST_URL = "/user";




    @PostMapping(value = "/notify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> notify(@RequestBody @Valid User user) {
        User created=service.create(user);
        URI uriOfNewResource=ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL+"/{id}")
                    .buildAndExpand(created.getId()).toUri();
     return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
