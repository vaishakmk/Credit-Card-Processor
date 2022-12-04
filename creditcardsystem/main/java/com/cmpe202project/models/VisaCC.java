package com.sjsu.designpattern.model;

import java.util.Date;

public class VisaCC extends CreditCard {

    public VisaCC(String cardNumber, Date expirationDate, String nameOfCardholder){
        super(CreditCardType.Visa, cardNumber, expirationDate, nameOfCardholder);
        System.out.println("Visa Credit Card Created");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
