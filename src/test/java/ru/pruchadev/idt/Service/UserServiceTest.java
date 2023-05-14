package ru.pruchadev.idt.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {
    @Autowired
    protected UserService service;
    @Test
    void create() throws Exception {
       User newUser =new User(null,"User1","BTC",26100);
       User created=service.create(newUser);
        newUser.setId(created.getId());
        assertThat(created).isEqualToIgnoringGivenFields(newUser, "currency");

    }
}