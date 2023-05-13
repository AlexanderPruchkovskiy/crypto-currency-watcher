package ru.pruchadev.idt.web.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.pruchadev.idt.model.Currency;
import ru.pruchadev.idt.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = RestCurrencyController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestCurrencyController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected CurrencyService service;

    public static final String REST_URL = "/currencies";


        @GetMapping
        public List<Currency> getAll() {
            return service.getAll();
        }


        @GetMapping("/{symbol}")
        public Currency get(@PathVariable String symbol ) {
            return service.get(symbol);
        }

}
