package ru.pruchadev.idt.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.pruchadev.idt.model.Currency;
import ru.pruchadev.idt.model.User;
import ru.pruchadev.idt.repository.CurrencyRepository;
import ru.pruchadev.idt.repository.UserRepository;

import java.util.*;

import static java.lang.Math.abs;


@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private static final int[] CURRENCIES_CODES = new int[]{90, 80, 48543};
    private final String coinloreURL = "https://api.coinlore.net/api/ticker/?id=";
    private final Logger log = LoggerFactory.getLogger(getClass());

    public CurrencyService(CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    public void getCurrencyFromCoinloreApi() throws JsonProcessingException {


        for (int code : CURRENCIES_CODES) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(coinloreURL + code);
            Integer currency_code = Integer.parseInt(jsonNode.get("id").toString());
            String symbol = jsonNode.get("symbol").toString();
            double currentPrice = Double.parseDouble(jsonNode.get(("price_usd")).toString());
            Currency currency = new Currency(currentPrice, currency_code, symbol);
            if (currency != null) {
                currencyRepository.save(currency);
            }

        }

    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Currency get(String symbol) {
        Optional<Currency> currency = currencyRepository.getBySymbol(symbol);
        return currency.isEmpty() ? null : currency.get();
    }

    private double comparePriceMoreOnePercent(Currency currency, User user) {
        double currentCurrencyPrice = currency.getCurrentPrice();
        double userRegPrice = user.getRegPrice();
        double result =Math.abs(1 - userRegPrice / currentCurrencyPrice) * 100 ;
        if (result > 1) return result;
        else return 0;
    }

    private List<Currency> currenciesForAllExistUsers() {
        List<Currency> currencies = new ArrayList<>();
        List<Currency> currenciesResult = new ArrayList<>();
        currencies = currencyRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        currencies.stream().forEach(currency -> {
            users.iterator().forEachRemaining(user -> {
                if (user.getSymbol().equals(currency.getSymbol())) currenciesResult.add(currency);
            });
        });
        return currenciesResult;
    }

    public void updatePriceForUser(){
        List<Currency> currencyList=currenciesForAllExistUsers();
        Iterable<User>userIterable=userRepository.findAll();
        List<User>users=new ArrayList<>();
        double percentOfGrowth=0;
        User user;
        userIterable.iterator().forEachRemaining(users::add);
        for (Currency currency : currencyList) {
            user=users.stream().filter(u -> u.getSymbol().equals(currency.getSymbol()))
                    .findFirst().get();
            percentOfGrowth=comparePriceMoreOnePercent(currency, user);

          if  (percentOfGrowth!=0) {
              toWarnLog (currency.getSymbol(),user.getUserName(),percentOfGrowth);
              user.setRegPrice(currency.getCurrentPrice());
              userRepository.save(user);
          }
        }
    }

       private void toWarnLog(String symbol, String userName, double percentOfGrowth){
        log.warn("User with name: %s, Crypto price with symbol: %s has changed by -%.2f%% "+"\n", userName, symbol,percentOfGrowth);
       }
}