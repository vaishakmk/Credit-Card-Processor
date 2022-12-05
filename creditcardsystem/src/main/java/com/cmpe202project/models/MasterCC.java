package com.cmpe202project.models;

import java.util.Date;


public class MasterCC extends CreditCard {

    public MasterCC(String cardNumber, Date expirationDate, String nameOfCardholder){
        super(CreditCardType.MasterCard, cardNumber, expirationDate, nameOfCardholder);
        System.out.println("Mastercard Credit Card Created");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
