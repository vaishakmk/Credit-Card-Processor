package com.cmpe202project.models;

import java.util.Date;

public class DiscoverCC extends CreditCard {

    public DiscoverCC(String cardNumber, Date expirationDate, String nameOfCardholder){
        super(CreditCardType.Discover, cardNumber, expirationDate, nameOfCardholder);
        System.out.println("Discover Credit Card Created");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
