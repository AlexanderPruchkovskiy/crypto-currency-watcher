package ru.pruchadev.idt.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.pruchadev.idt.model.Currency;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.repository.CurrencyRepository;
import ru.pruchadev.idt.service.CurrencyService;
import ru.pruchadev.idt.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;




@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CurrencyServiceTest {


    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void getAll() {

    }

    @Test
    void get() throws Exception{

            Currency currency = currencyService.get("BTC");
            assertEquals("BTC",currency.getSymbol());
    }

    @Test
    void updatePriceForUser() {

        User newUser =new User(null,"User1","BTC",27000);
        User userCreated=userService.create(newUser);
        userCreated.setRegPrice(27297);
        Currency currency=currencyService.get("BTC");
        currency.setCurrentPrice(27297);
        currencyRepository.save(currency);
        currencyService.updatePriceForUser();
        User actualUser=userService.get("User1");
        assertEquals(userCreated.getRegPrice(),actualUser.getRegPrice());

    }
}
