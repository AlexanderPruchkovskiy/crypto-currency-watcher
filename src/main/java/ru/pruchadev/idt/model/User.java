package ru.pruchadev.idt.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = {"currency_id","userName"},
                                           name = "users_unique_currency_userName_idx" )})
public class User extends AbstractNamedEntity {

    public User() {
    }
    public User(Integer id, String userName, @NotBlank String symbol, @NotNull double regPrice) {
        super(id, userName);
        this.symbol = symbol;
        this.regPrice = regPrice;
    }

    @Column(name = "symbol", nullable = false, unique = true)
    @NotBlank
    private String symbol;

    @Column(name = "regPrice", nullable = false)
    @NotNull
    private double regPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Currency currency;

    public void setRegPrice(double regPrice) {
        this.regPrice = regPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getRegPrice() {
        return regPrice;
    }

    public String getUserName() {
        return userName;
    }
}
