package ru.pruchadev.idt.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.server.ResponseStatusException;
import ru.pruchadev.idt.service.CurrencyService;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulerConfig {
    private final CurrencyService currencyService;

    public SchedulerConfig(CurrencyService currencyService){
        this.currencyService=currencyService;
    }
    @Scheduled(fixedRate = 60000)
    @Async
    public void refreshCurrency(){
        try{
        currencyService.getCurrencyFromCoinloreApi();}
        catch (JsonProcessingException e){throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                "PROBLEM WITH TO COINLORE REQUEST!");
        }
        currencyService.updatePriceForUser();
    }
}
