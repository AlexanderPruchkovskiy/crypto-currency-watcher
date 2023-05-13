package ru.pruchadev.idt.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.pruchadev.idt.model.Currency;

import java.util.List;
import java.util.Optional;


 public     interface CurrencyRepository extends CrudRepository<Currency, String> {
       List<Currency> findAll();

      Optional<Currency>  getBySymbol(String symbol);
     }
