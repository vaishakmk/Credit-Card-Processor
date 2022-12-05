package com.cmpe202project.models;

import java.util.Date;

public class AmExCC extends CreditCard {

    public AmExCC(String cardNumber, Date expirationDate, String nameOfCardholder){
        super(CreditCardType.AmericanExpress, cardNumber, expirationDate, nameOfCardholder);
        System.out.println("American Express Credit Card Created");
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
