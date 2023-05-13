package ru.pruchadev.idt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "currencies", uniqueConstraints = {@UniqueConstraint(columnNames =  "currency_code", name = "currencies_unique_currency_code_idx")})
public class Currency extends AbstractBaseEntity{

    public Currency(@NotNull double currentPrice, @NotNull Integer currency_code, @NotBlank String symbol) {
        this.currentPrice = currentPrice;
        this.currency_code = currency_code;
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    @Column(name = "currentPrice", nullable = false)
    @NotNull
    private double currentPrice;

    @Column(name = "currency_code", nullable = false)
    @NotNull
    private Integer currency_code;

    @Column(name="symbol", nullable = false, unique = true)
    @NotBlank
    private String symbol;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    @OrderBy("userName DESC")
    protected List<User> users;
}
